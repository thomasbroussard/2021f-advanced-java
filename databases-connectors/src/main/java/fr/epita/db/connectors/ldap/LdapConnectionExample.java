package fr.epita.db.connectors.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Hashtable;

public class LdapConnectionExample {

    public static void main(String[] args) throws NamingException {

        Hashtable<String, String> ldapEnv = getCommonLdapParameters();
        DirContext adminContext = bind(ldapEnv,  "uid=admin,ou=system", "secret");

        SearchControls searchCtls = new SearchControls();

        String returnedAtts[]={"cn","manager"};
        searchCtls.setReturningAttributes(returnedAtts);

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        //specify the LDAP search filter
        String searchFilter = "(objectClass=inetOrgPerson)";


        //Specify the Base for the search
        String searchBase = "dc=example,dc=com";
        //initialize counter to total the results
        int totalResults = 0;

        // Search for objects using the filter
        NamingEnumeration<SearchResult> answer = adminContext.search(searchBase, searchFilter, searchCtls);
        answer.asIterator().forEachRemaining(s -> System.out.println(s.getAttributes()));

        adminContext.close();

        DirContext userContext = bind(ldapEnv,  "cn=chandana,ou=users,dc=example,dc=com", "secret");
        userContext.close();



    }

    private static DirContext bind(Hashtable<String, String> ldapEnv, String userDN, String userPassword) throws NamingException {
        ldapEnv.put(Context.SECURITY_PRINCIPAL, userDN);
        ldapEnv.put(Context.SECURITY_CREDENTIALS, userPassword);

        DirContext context = new InitialDirContext(ldapEnv);
        return context;
    }

    private static Hashtable<String, String> getCommonLdapParameters() {
        Hashtable<String,String> ldapEnv = new Hashtable<>();

        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        ldapEnv.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        return ldapEnv;
    }
}
