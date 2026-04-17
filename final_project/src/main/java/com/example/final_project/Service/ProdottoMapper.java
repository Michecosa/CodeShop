package com.example.final_project.Service;

/**
 * Trasformare prodotti in dto e viceversa
 */
@Service
public class ProdottoMapper {
    public ProdottoDto toDto(Prodotto prodotto) {
        // //full esplicito
        // //estraggo tutti i dati
        // Long idProdotto = prodotto.getId();
        // String nomeProdotto = prodotto.getNome();
        // double prezzoProdotto = prodotto.getPrezzo();
        // //applico trasformazioni necessarie
        // List<String> categorie = new ArrayList<>();
        //
        // for(Categoria categoria : prodotto.getCategorie())
        // categorie.add(categoria.getNome());
        //
        // //creo dto con quei dati
        // ProdottoDto dto = new ProdottoDto(idProdotto, nomeProdotto, prezzoProdotto,
        // categorie);
        // return dto;
        return new ProdottoDto(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getCategorie().stream().map(c -> c.getNome()).toList());

    }

    public List<ProdottoDto> toDtos(List<Prodotto> prodotti) {
        // List<ProdottoDto> res = new ArrayList<>();
        //
        // for(Prodotto prodotto: prodotti)
        // res.add(toDto(prodotto));
        // return res;
        return prodotti.stream().map(p -> toDto(p)).toList();
    }
}
