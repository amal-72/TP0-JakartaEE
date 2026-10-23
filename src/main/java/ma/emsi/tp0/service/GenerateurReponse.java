
package ma.emsi.tp0.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

@Dependent
public class GenerateurReponse implements Serializable {

    public String genererReponse(String question, String roleSysteme) {
        String texte = question.toLowerCase(Locale.FRENCH).trim();

        String role = roleSysteme == null
                ? ""
                : roleSysteme.toLowerCase(Locale.ROOT);

        String resultat;

        if (role.contains("interpreter")) {
            resultat = traduire(texte);

        } else if (role.contains("travel guide")) {
            resultat = guider(texte);

        } else {
            resultat = repondreAssistant(texte);
        }

        return "||" + resultat + "||";
    }

    private String repondreAssistant(String texte) {
        if (texte.contains("java")) {
            return "Java est un langage de programmation "
                    + "utilisé pour développer des applications.";

        } else if (texte.contains("git")) {
            return "Git est un outil de gestion de versions "
                    + "qui permet de suivre les modifications du code.";

        } else if (texte.contains("machine learning")
                || texte.contains("apprentissage automatique")) {
            return "Le machine learning est une branche de l'IA "
                    + "qui permet aux systèmes d'apprendre à partir de données.";

        } else if (texte.contains("intelligence artificielle")
                || texte.contains("c'est quoi l'ia")
                || texte.contains("c est quoi l ia")) {
            return "L'intelligence artificielle regroupe des techniques "
                    + "permettant à des systèmes d'effectuer des tâches "
                    + "qui nécessitent habituellement des capacités humaines.";
        }

        return "Bonne question ! Pour réviser l'informatique, "
                + "essaie de préciser le concept : Java, Git, "
                + "intelligence artificielle ou machine learning.";
    }

    private String traduire(String texte) {
        return switch (texte) {
            case "bonjour" -> "Hello.";

            case "bonjour, comment allez-vous ?",
                 "bonjour comment allez-vous" ->
                    "Hello, how are you?";

            case "merci" -> "Thank you.";

            case "au revoir" -> "Goodbye.";

            case "hello" -> "Bonjour.";

            case "hello, how are you?" ->
                    "Bonjour, comment allez-vous ?";

            case "thank you" -> "Merci.";

            case "goodbye" -> "Au revoir.";

            default -> "Je ne connais pas encore cette phrase. "
                    + "Essaie : Bonjour, Merci, Hello ou Thank you.";
        };
    }

    private String guider(String texte) {
        if (texte.contains("marrakech")) {
            return "À Marrakech, visite la place Jemaa el-Fna, "
                    + "la médina, le palais de la Bahia et le jardin Majorelle. "
                    + "Pour un repas simple, prévois environ 40 à 100 MAD, "
                    + "selon le restaurant.";

        } else if (texte.contains("casablanca")) {
            return "À Casablanca, visite la mosquée Hassan II, "
                    + "la corniche d'Aïn Diab et le quartier des Habous. "
                    + "Pour un repas simple, prévois environ 40 à 100 MAD, "
                    + "selon le restaurant.";

        } else if (texte.contains("paris")) {
            return "À Paris, tu peux visiter la tour Eiffel, "
                    + "le musée du Louvre et Montmartre. "
                    + "Un repas simple peut coûter environ 15 à 25 euros, "
                    + "selon le restaurant.";

        } else {
            return "Je peux te conseiller des visites à Marrakech, "
                    + "Casablanca ou Paris. Indique le nom d'une de ces villes.";
        }
    }
}
