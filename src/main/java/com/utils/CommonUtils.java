package com.utils;

import java.io.InputStream;
import java.security.cert.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.Enumeration;
import javax.xml.bind.DatatypeConverter;




public class CommonUtils {
	/* Get the thumbprint from certificate */
	private static String getThumbprint(X509Certificate cert) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] der = cert.getEncoded();
			md.update(der);
			byte[] digest = md.digest();
			String digestHex = DatatypeConverter.printHexBinary(digest);
			return digestHex.toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/* Get the private key  from keystore */
	public static String getKey(InputStream key, String keyPassword) {
		KeyStore keystore;
		PrivateKey privateKey = null;
		String alias = null;
		String keypem = null;

		try {
			//Loading the keystore keystore type is .pfx
			keystore = KeyStore.getInstance("PKCS12");
			keystore.load(key,
					keyPassword.toCharArray());
			
			//getting the alias of the certificate
			Enumeration<String> enumeration = keystore.aliases();
			while (enumeration.hasMoreElements()) {
				alias = enumeration.nextElement();
				//System.out.println("alias name: " + alias);
			}
			
			//getting the private key
			privateKey = (PrivateKey) keystore.getKey(alias, keyPassword.toCharArray());
			
			//converting it to pem format
			keypem = "-----BEGIN PRIVATE KEY-----" + DatatypeConverter.printBase64Binary(privateKey.getEncoded())
					+ "-----END PRIVATE KEY-----";
			
			//System.out.println("keypem:" + keypem);
			
			//returning private key in pem format 
			return keypem;
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return null;
	}
	/* Get the thumbprint  from keystore */
	public static String getThumbprintHex(InputStream key, String keyPassword) {
		KeyStore keystore;
		String thumbprint = null;
		String alias = "";

		try {
			
			/*Loading the keystore keystore type is .pfx*/
			keystore = KeyStore.getInstance("PKCS12");
			keystore.load(key,
					keyPassword.toCharArray());
			
			/*getting the alias of the certificate*/
			Enumeration<String> enumeration = keystore.aliases();
			while (enumeration.hasMoreElements()) {
				alias = enumeration.nextElement();
			}
			
			/*getting the certificate*/
			X509Certificate x509Certificate = (X509Certificate) keystore.getCertificate(alias);
			
			/* getting certificate thumbprint*/
			thumbprint = getThumbprint(x509Certificate);
			
			//System.out.println("Thumbprint:" + thumbprint);
			
			return thumbprint;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
