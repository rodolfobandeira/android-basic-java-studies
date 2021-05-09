package br.com.alura.estoque.repository;

import java.util.List;

import br.com.alura.estoque.asynctask.BaseAsyncTask;
import br.com.alura.estoque.database.dao.ProdutoDAO;
import br.com.alura.estoque.model.Produto;
import br.com.alura.estoque.retrofit.EstoqueRetrofit;
import br.com.alura.estoque.retrofit.callback.CallbackComRetorno;
import br.com.alura.estoque.retrofit.callback.CallbackSemRetorno;
import br.com.alura.estoque.retrofit.service.ProdutoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ProdutoRepository {
    private final ProdutoDAO dao;
    private final ProdutoService service;

    public ProdutoRepository(ProdutoDAO dao) {
        this.dao = dao;
        service = new EstoqueRetrofit().getProdutoService();
    }



    /* --------------- BUSCA PRODUTOS -------------- */

    public void buscaProdutos(DadosCarregadosCallback<List<Produto>> callback) {
        buscaProdutosInternos(callback);
    }

    private void buscaProdutosInternos(DadosCarregadosCallback<List<Produto>> callback) {
        new BaseAsyncTask<>(dao::buscaTodos,
                resultado -> {
                    callback.quandoSucesso(resultado);
                    buscaProdutosNaApi(callback);
                }).execute();
    }

    private void buscaProdutosNaApi(DadosCarregadosCallback<List<Produto>> callback) {
        Call<List<Produto>> call = service.buscaTodos();
        call.enqueue(new Callback<List<Produto>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Produto>> call,
                                   Response<List<Produto>> response) {
                if (response.isSuccessful()) {
                    List<Produto> produtosNovos = response.body();
                    if (produtosNovos != null) {
                        atualizaInterno(produtosNovos, callback);
                    }
                } else {
                    // Notify in case of failure
                    callback.quandoFalha("Erro ao tentar buscar produtos via API");
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Produto>> call,
                                  Throwable t) {
                // Notify on failure
                callback.quandoFalha("Falha de comunicacao: " + t.getMessage());
            }
        });
    }



    /* --------------- SAVE -------------- */

    public void salva(Produto produto,
                      DadosCarregadosCallback<Produto> callback) {
        salvaNaApi(produto, callback);
    }

    private void salvaNaApi(Produto produto,
                            DadosCarregadosCallback<Produto> callback) {
        Call<Produto> call = service.salva(produto);

        call.enqueue(new CallbackComRetorno<>(
                new CallbackComRetorno.RespostaCallback<Produto>() {
            @Override
            public void quandoSucesso(Produto resultado) {
                salvaInteno(resultado, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void salvaInteno(Produto produtoSalvo,
                             DadosCarregadosCallback<Produto> callback) {
        new BaseAsyncTask<>(() -> {
            long id = dao.salva(produtoSalvo);
            return dao.buscaProduto(id);
        }, callback::quandoSucesso).execute();
    }


    /* --------------- EDITA -------------- */

    public void edita(Produto produto,
                      DadosCarregadosCallback<Produto> callback) {
        editaNaApi(produto, callback);
    }

    private void editaNaApi(Produto produto, DadosCarregadosCallback<Produto> callback) {
        Call<Produto> call = service.edita(produto.getId(), produto);
        call.enqueue(new CallbackComRetorno<>(new CallbackComRetorno.RespostaCallback<Produto>() {
            @Override
            public void quandoSucesso(Produto resultado) {
                editaInterno(produto, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void editaInterno(Produto produto, DadosCarregadosCallback<Produto> callback) {
        new BaseAsyncTask<>(() -> {
            dao.atualiza(produto);
            return produto;
        }, callback::quandoSucesso).execute();
    }


    private void atualizaInterno(List<Produto> produtosNovos, DadosCarregadosCallback<List<Produto>> callback) {
        new BaseAsyncTask<>(() -> {
            dao.salva(produtosNovos);
            return dao.buscaTodos();
        }, callback::quandoSucesso)
                .execute();
    }


    /* --------------- REMOVE -------------- */

    public void remove(Produto produto,
                       DadosCarregadosCallback<Void> callback) {
        removeNaApi(produto, callback);
    }

    private void removeNaApi(Produto produto, DadosCarregadosCallback<Void> callback) {
        Call<Void> call = service.remove(produto.getId());
        call.enqueue(
                new CallbackSemRetorno(new CallbackSemRetorno.RespostaCallback() {
            @Override
            public void quandoSucesso() {
                removeInternal(produto, callback);
            }

            @Override
            public void quandoFalha(String erro) {
                callback.quandoFalha(erro);
            }
        }));
    }

    private void removeInternal(Produto produto, DadosCarregadosCallback<Void> callback) {
        new BaseAsyncTask<>(() -> {
            dao.remove(produto);
            return null;
        }, callback::quandoSucesso)
                .execute();
    }



    /* --------------- INTERFACE -------------- */

    public interface DadosCarregadosCallback <T> {
        void quandoSucesso(T sucesso);
        void quandoFalha(String erro);
    }
}

