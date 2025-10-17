package br.com.fiap.ecommerce.vuln

import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class VulnController {

    /**
     * Rota propositalmente vulnerável (REFLECTED XSS).
     * Exemplo de uso:
     *  http://localhost:8080/vuln/echo?q=<script>alert(1)</script>
     *
     * OBS: Isso é inseguro de propósito — NÃO deixar em produção.
     */
    @GetMapping("/vuln/echo")
    fun echo(@RequestParam("q", required = false) q: String?, response: HttpServletResponse): String {
        response.contentType = "text/html; charset=UTF-8"
        val payload = q ?: ""
        // Retorna HTML sem qualquer escape -> XSS refletido
        return "<html><body><h3>Echo</h3><div>$payload</div></body></html>"
        // Teste de vulnerabilidade
    }
}