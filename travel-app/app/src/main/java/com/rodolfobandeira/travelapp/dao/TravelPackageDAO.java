package com.rodolfobandeira.travelapp.dao;

import com.rodolfobandeira.travelapp.model.TravelPackage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelPackageDAO {
    public List<TravelPackage> list() {
        List<TravelPackage> packages = new ArrayList<>(Arrays.asList(
                new TravelPackage("Sao Paulo", "sao_paulo_sp", 2, new BigDecimal(245.99)),
                new TravelPackage("Recife", "recife_pe", 3, new BigDecimal(325.99)),
                new TravelPackage("Salvador", "salvador_ba", 2, new BigDecimal(125.99)),
                new TravelPackage("Belo Horizonte", "belo_horizonte_mg", 4, new BigDecimal(450)),
                new TravelPackage("Rio de Janeiro", "rio_de_janeiro_rj", 5, new BigDecimal(725.99))));
        return packages;
    }
}
