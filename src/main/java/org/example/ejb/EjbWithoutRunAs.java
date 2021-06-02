package org.example.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

@LocalBean
@Stateless
public class EjbWithoutRunAs {
    @Inject
    private SecurityContext securityContext;

    public void test() {
        System.out.println("ejbWithoutRunAs.test isSystem: " + securityContext.isCallerInRole("System"));
        System.out.println("ejbWithoutRunAs.test isAdmin: " + securityContext.isCallerInRole("Admin"));
        System.out.println();
    }

    public void testFromRunAsSystem() {
        System.out.println("ejbWithoutRunAs.testFromRunAsSystem isSystem: " + securityContext.isCallerInRole("System"));
        System.out.println("ejbWithoutRunAs.testFromRunAsSystem isAdmin: " + securityContext.isCallerInRole("Admin"));
        System.out.println();
    }
}
