// Pacote: br.com.uninassau.projetoseau.controller
package br.com.uninassau.ProjetoSEAU.controleHTTP;

import br.com.uninassau.ProjetoSEAU.modelos.Conta;
import br.com.uninassau.ProjetoSEAU.modelos.Login;
import br.com.uninassau.ProjetoSEAU.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {
    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody Conta conta) {
        if (contaService.cadastrarConta(conta)) {
            return new ResponseEntity<>("Cadastro realizado com sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("CPF já cadastrado.", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        if (contaService.autenticar(login)) {
            return new ResponseEntity<>("Login realizado com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("CPF ou senha incorretos.", HttpStatus.UNAUTHORIZED);
        }
    }
}

