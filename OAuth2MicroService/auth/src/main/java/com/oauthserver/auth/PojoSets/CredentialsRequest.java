package com.oauthserver.auth.PojoSets;

public class CredentialsRequest {
    private String usernameBinding;
    private String passwordBinding;

    public CredentialsRequest(){}

    public String getUsernameBinding() {
        return this.usernameBinding;
    }

    public void setUsernameBinding(String usernameBinding) {
        this.usernameBinding = usernameBinding;
    }

    public String getPasswordBinding() {
        return this.passwordBinding;
    }

    public void setPasswordBinding(String passwordBinding) {
        this.passwordBinding = passwordBinding;
    }


    @Override
    public String toString() {
        return "{" +
            " usernameBinding='" + getUsernameBinding() + "'" +
            ", passwordBinding='" + getPasswordBinding() + "'" +
            "}";
    }


}
