package com.porto.spi_authenticator_resource_owner;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

public class AuthenticatorPortoResourceOwnerFactory implements AuthenticatorFactory {

    public static final String PROVIDER_ID = "porto-resource-owner";
    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();

    static {
        ProviderConfigProperty urlAuthentication = new ProviderConfigProperty();
        urlAuthentication.setName("urlAuthentication");
        urlAuthentication.setLabel("URL Authentication");
        urlAuthentication.setType(ProviderConfigProperty.STRING_TYPE);
        urlAuthentication.setHelpText("The URL to authenticate the user. This is used for microservice authentication.");
        configProperties.add(urlAuthentication);

        ProviderConfigProperty enableSecondFactor = new ProviderConfigProperty();
        enableSecondFactor.setName("enableSecondFactor");
        enableSecondFactor.setLabel("Enable Second Factor Authentication");
        enableSecondFactor.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        enableSecondFactor.setHelpText("Enable or disable second factor authentication for the resource owner flow.");
        configProperties.add(enableSecondFactor);

    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.copyOf(configProperties);
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        return new AuthenticatorPortoResourceOwner();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Porto Authenticator Resource Owner";
    }

    @Override
    public String getHelpText() {
        return "Authenticate microservice users using Resource Owner Password Credentials flow.";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getReferenceCategory() {
        return "";
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.DISABLED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE,

        };
    }
}
