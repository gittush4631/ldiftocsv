package ldiftocsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.ldif.LdifReader;

public class CleanLDIF {
	public static void main(String[] args) throws LdapLdifException, IOException {
		File f = new File("/Users/tusharpatil/test/user_manager_export.ldif");
		FileReader fread = new FileReader(f);
		LdifReader lr = new LdifReader();
		String cn,uid,manager,managerid,firstdn;
		File f1=new File("/Users/tusharpatil/test/dn.csv");
		FileWriter fwrite=new FileWriter(f1);
		
		int i=0;
		List<LdifEntry> entries = lr.parseLdifFile("/Users/tusharpatil/test/user_manager_export.ldif");
		//String dn = "erglobalid=6827793839183019401,ou=0,ou=people,erglobalid=00000000000000000000, ou=YBL, DC=YESBANK,DC=IN";
		String dn = "erglobalid=8077966300291128451,ou=0,ou=people,erglobalid=00000000000000000000, ou=YBL, DC=YESBANK,DC=IN";
		//iterate the entries
		for (LdifEntry entry : entries) {
		    firstdn = entry.getDn().toString();
		    
		    if (firstdn.equals(dn)) {
		        cn=entry.get("cn").toString();
		        System.out.println(cn);
		    }
		    //System.out.println("DN name"+name);
		   // System.out.println(firstdn);
		    
		   fwrite.write(firstdn);
		   fwrite.write("\n");
		        
		    }
		System.out.println("Not found");
		}
		
		}
		
		
	


