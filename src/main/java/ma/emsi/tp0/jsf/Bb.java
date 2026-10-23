package ma.emsi.tp0.jsf;

import ma.emsi.tp0.service.GenerateurReponse;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class Bb implements Serializable {

    private String roleSysteme;
    private boolean roleSystemeChangeable = true;
    private List<SelectItem> listeRolesSysteme;

    private String question;
    private String reponse;
    private StringBuilder conversation = new StringBuilder();

    @Inject
    private GenerateurReponse generateurReponse;

    @Inject
    private FacesContext facesContext;

    public Bb() {
    }

    public String getRoleSysteme() {
        return roleSysteme;
    }

    public void setRoleSysteme(String roleSysteme) {
        this.roleSysteme = roleSysteme;
    }

    public boolean isRoleSystemeChangeable() {
        return roleSystemeChangeable;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getConversation() {
        return conversation.toString();
    }

    public void setConversation(String conversation) {
        this.conversation = new StringBuilder(
                conversation == null ? "" : conversation
        );
    }

    public String envoyer() {
        if (question == null || question.isBlank()) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Texte question vide",
                    "Il manque le texte de la question"
            );

            facesContext.addMessage(null, message);
            return null;
        }

        if (roleSysteme == null || roleSysteme.isBlank()) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Rôle manquant",
                    "Veuillez sélectionner un rôle système."
            );

            facesContext.addMessage(null, message);
            return null;
        }

        if (conversation.isEmpty()) {
            roleSystemeChangeable = false;
        }

        reponse = generateurReponse.genererReponse(
                question,
                roleSysteme
        );

        afficherConversation();

        question = null;

        return null;
    }

    public String nouveauChat() {
        return "index?faces-redirect=true";
    }

    private void afficherConversation() {
        conversation
                .append("== User:\n")
                .append(question == null ? "" : question)
                .append("\n== Serveur:\n")
                .append(reponse == null ? "" : reponse)
                .append("\n");
    }

    public List<SelectItem> getRolesSysteme() {
        if (listeRolesSysteme == null) {
            listeRolesSysteme = new ArrayList<>();

            String role = """
                    You are a helpful assistant. You help the user to find the information they need.
                    If the user type a question, you answer it.
                    """;

            listeRolesSysteme.add(
                    new SelectItem(role, "Assistant")
            );

            role = """
                    You are an interpreter. You translate from English to French and from French to English.
                    If the user type a French text, you translate it into English.
                    If the user type an English text, you translate it into French.
                    If the text contains only one to three words, give some examples of usage of these words in English.
                    """;

            listeRolesSysteme.add(
                    new SelectItem(role, "Traducteur Anglais-Français")
            );

            role = """
                    You are a travel guide. If the user type the name of a country or of a town,
                    you tell them what are the main places to visit in the country or the town
                    and tell them the average price of a meal.
                    """;

            listeRolesSysteme.add(
                    new SelectItem(role, "Guide touristique")
            );
        }

        return listeRolesSysteme;
    }
}
