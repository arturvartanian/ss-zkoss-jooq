package authorizationzkjooq.model.service;

public class UserCredential {

    private String email;

    private boolean anonymous;

    public UserCredential() {
        anonymous = true;
    }

    public UserCredential(String email) {
        this.email = email;
        anonymous = false;
    }

    public boolean isAnonymous(){
        return anonymous;
    }

    public String getEmail() {
        return email;
    }

}
