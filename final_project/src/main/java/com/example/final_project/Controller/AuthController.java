package com.example.final_project.Controller;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder criptatore;
	@Autowired
	private UtenteRepository dao;
	@Autowired
	private CarrelloRepository cDao;
	@Autowired
	private AuthenticationManager authManager;

	@PostMapping("/register")
	public void register(@RequestBody RegistrationDto dto) {
		Utente daCreare = new Utente();
		daCreare.setUsername(dto.username());
		daCreare.setPassword(criptatore.encode(dto.password));
		daCreare.setMail(dto.mail());
		daCreare.setRoles("ROLE_USER");

		daCreare = dao.save(daCreare);

		Carrello c = new Carrello();
		c.setUtente(daCreare);
		cDao.save(c);
	}

	@PostMapping("/login")
	public JwtResponseDto login(@RequestBody LoginDto dto) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

		String tokenizzato = jwtService.generateToken(auth);

		return new JwtResponseDto(tokenizzato);

	}

	@GetMapping("/test")
	public String dammiNomeUtente(Authentication auth)// lui lo riempie in automatico con l'utente a cui appartiene il
														// JWT
	{
		String nomeUtente = auth.getName();

		return nomeUtente;
	}

	// Se un DTO è SEMPLICE e utilizzato in un solo Controller lo potete mettere
	// direttamente nel controller stesso

	public record LoginDto(
			String username,
			String password) {
	}

	public record RegistrationDto(
			String username,
			String mail,
			String password) {
	}

	public record JwtResponseDto(
			String token) {
	}

}
