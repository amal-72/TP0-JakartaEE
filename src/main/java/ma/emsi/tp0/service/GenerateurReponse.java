
package ma.emsi.tp0.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

/**
 * Classe de service pour les modificateurs de question.
 * Un modificateur de question prend en entrée une question et retourne
 * la question modifiée.
 */
@Dependent
public class GenerateurReponse implements Serializable {

    /**
     * Génère une réponse à une question.
     *
     * @param question La question à modifier.
     * @param roleSysteme Le rôle système à utiliser pour la modification.
     * @return La question modifiée.
     */
    public String genererReponse(String question, String roleSysteme) {
        String resultat = "||";

        if (roleSysteme != null) {
            resultat += roleSysteme.toUpperCase(Locale.FRENCH) + "\n";
        }

        resultat += question.toLowerCase(Locale.FRENCH) + "||";

        return resultat;
    }
}
