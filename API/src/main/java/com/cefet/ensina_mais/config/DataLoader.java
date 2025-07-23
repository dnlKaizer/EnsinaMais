package com.cefet.ensina_mais.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cefet.ensina_mais.entities.Usuario;
import com.cefet.ensina_mais.entities.NivelAcesso;
import com.cefet.ensina_mais.repositories.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se o usuário admin já existe
        if (!usuarioRepository.existsByLogin("admin")) {
            // Criar usuário admin com senha criptografada
            Usuario admin = new Usuario();
            admin.setLogin("admin");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setNivelAcesso(NivelAcesso.ADMIN);
            
            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado com sucesso!");
        } else {
            System.out.println("Usuário admin já existe no banco de dados.");
        }
    }
}
