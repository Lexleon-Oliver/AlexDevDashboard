package br.net.alexdev.dashboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável por rotear as solicitações relacionadas às visualizações na aplicação.
 */
@Controller
public class ViewController {
    /**
     * Retorna a página principal da aplicação.
     * <p>
     * Método HTTP: GET
     * Endpoint: /
     *
     * @param model O modelo de dados para a página.
     * @return A página "index".
     */
    @GetMapping("/")
    public String getMainPage(Model model) {
        return "index";
    }

    /**
     * Retorna a página de login da aplicação.
     * <p>
     * Método HTTP: GET
     * Endpoint: /login
     *
     * @param model O modelo de dados para a página.
     * @return A página "index".
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "index";
    }
}
