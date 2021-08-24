package com.zlab.commune.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;


public class RemoteUserStorageProviderFactory implements UserStorageProviderFactory<RemoteUserStorageProvider> {

    public static final String PROVIDER_NAME = "commune-user-storage-provider";

    @Override
    public RemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new RemoteUserStorageProvider(session, model, buidHttpClient("http://localhost:9099"));
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    private UserApiService buidHttpClient(String uri){

        ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
        ResteasyWebTarget resteasyWebTarget = resteasyClient.target(uri);

        return resteasyWebTarget.proxyBuilder(UserApiService.class)
                .classloader(UserApiService.class.getClassLoader())
                .build();
    }
}
