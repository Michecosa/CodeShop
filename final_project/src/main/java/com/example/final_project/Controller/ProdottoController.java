package com.example.final_project.Controller;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProdottoController {
    private final ProdottoService serv;

    @GetMapping
    public List<ProdottoDto> getAll() {
        return serv.leggiTuttiProdottiComeDto();
    }

}
