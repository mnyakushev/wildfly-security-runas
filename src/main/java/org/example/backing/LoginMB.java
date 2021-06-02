package org.example.backing;

import org.example.ejb.EjbRunAsSystem;
import org.example.ejb.EjbWithoutRunAs;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Named
@RequestScoped
public class LoginMB {
    @Inject
    private SecurityContext securityContext;

    @Inject
    private EjbWithoutRunAs ejbWithoutRunAs;
    @Inject
    private EjbRunAsSystem ejbRunAsSystem;

    public void login(ActionEvent event) {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        Credential credential = new UsernamePasswordCredential("z", new Password("z"));
        AuthenticationStatus status = securityContext.authenticate(request, response,
                AuthenticationParameters.withParams().credential(credential));
    }

    public void test(ActionEvent event) {
        System.out.println("cdi isAdmin: " + securityContext.isCallerInRole("Admin"));
        System.out.println("cdi isSystem: " + securityContext.isCallerInRole("System"));
        System.out.println();

        ejbWithoutRunAs.test();
        ejbRunAsSystem.test();
        ejbRunAsSystem.testAnotherEjb();
    }

    public String getCurrentUserName() {
        Principal principal = securityContext.getCallerPrincipal();
        return principal == null ? "" : principal.getName();
    }

    public Boolean getIsAdmin() {
        return securityContext.isCallerInRole("Admin");
    }
}
