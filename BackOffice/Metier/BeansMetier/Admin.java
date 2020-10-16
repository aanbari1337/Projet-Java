package BeansMetier;

public class Admin {

    private String login_admin;
    private String mdp_admin;

    public Admin(String login_admin, String mdp_admin) {
        this.login_admin = login_admin;
        this.mdp_admin = mdp_admin;
    }

    public Admin() {
    }

    public String getLogin_admin() {
        return login_admin;
    }

    public void setLogin_admin(String login_admin) {
        this.login_admin = login_admin;
    }

    public String getMdp_admin() {
        return mdp_admin;
    }

    public void setMdp_admin(String mdp_admin) {
        this.mdp_admin = mdp_admin;
    }
}
