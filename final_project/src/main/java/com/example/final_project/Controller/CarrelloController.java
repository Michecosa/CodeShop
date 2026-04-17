package com.example.final_project.Controller;

@RestController
@RequestMapping("/api/carts")
public class CarrelloController {
    @Autowired
    private CarrelloService carrelloService;

    @GetMapping("/mine")
    public CarrelloPageDto getMine(Authentication authentication) {
        // Authentication viene in automatico riempito con l'utente a cui appartiene
        // il JWT, ne prendiamo lo username
        String username = authentication.getName();
        return carrelloService.getMyCarrello(username);
    }

    @PostMapping("/add")
    public CarrelloPageDto addProduct(@RequestBody AggiuntaDto dto, Authentication authentication) {
        return carrelloService.aggiungiProdotto(authentication.getName(), dto.idProdotto());
    }

    public record AggiuntaDto(Long idProdotto) {
    }
}
