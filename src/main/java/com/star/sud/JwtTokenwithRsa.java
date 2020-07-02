package com.star.sud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenwithRsa {

	private static final long VALIDITY_IN_MILLISECONDS = 24 * 60 * 60 * 1000;// 24 hrs
	private static final String ISSUER = "sudarshan";
	public static final String PUBLIC_KEY_FILE_PATH = "RSA/publicKey";
	public static final String PRIVATE_KEY_FILE_PATH = "RSA/privateKey";

	public void generateJWTWithRsa() throws NoSuchAlgorithmException, IOException {
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(1024);

		KeyPair kp = keyGenerator.genKeyPair();
		PublicKey publicKey = (PublicKey) kp.getPublic();
		PrivateKey privateKey = (PrivateKey) kp.getPrivate();

		String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		System.out.println("Public Key:");
		String convertToPublicKey = convertToPublicKey(encodedPublicKey);
		System.out.println(convertToPublicKey(encodedPublicKey));

		// Write To File - Public Key
		writeToFile(PUBLIC_KEY_FILE_PATH, convertToPublicKey);

		String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		String convertToPrivateKey = convertToPrivateKey(encodedPrivateKey);

		// Write To File - Private Key
		writeToFile(PRIVATE_KEY_FILE_PATH, convertToPrivateKey);

		// Generate Token
		String id = UUID.randomUUID().toString();
		String token = generateJwtToken(privateKey, VALIDITY_IN_MILLISECONDS, ISSUER, id);
		System.out.println("TOKEN:");
		System.out.println(token);

		// Print the claims
		printStructure(token, publicKey);
	}

	public String generateJwtToken(PrivateKey privateKey, long expirationInMillis, String issuer, String id) {
		JwtBuilder builder = Jwts.builder().setId(id).setIssuer(issuer).signWith(SignatureAlgorithm.RS256, privateKey);
		if (expirationInMillis >= 0) {
			long expMillis = System.currentTimeMillis() + expirationInMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		String token = builder.compact();
		return token;
	}

	// Print structure of JWT
	public void printStructure(String token, PublicKey publicKey) {
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);

		System.out.println("Header     : " + parseClaimsJws.getHeader());
		System.out.println("Body       : " + parseClaimsJws.getBody());
		System.out.println("Signature  : " + parseClaimsJws.getSignature());
	}

	// Add BEGIN and END comments
	private String convertToPublicKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PUBLIC KEY-----\n");
		result.append(key);
		result.append("\n-----END PUBLIC KEY-----");
		return result.toString();
	}

	// Add BEGIN and END comments
	private String convertToPrivateKey(String key) {
		StringBuilder result = new StringBuilder();
		result.append("-----BEGIN PRIVATE KEY-----\n");
		result.append(key);
		result.append("\n-----END PRIVATE KEY-----");
		return result.toString();
	}

	public void writeToFile(String path, String key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
		fos.write(key.getBytes());
		fos.flush();
		fos.close();
	}

	public static void main(String[] args) {
		try {
			JwtTokenwithRsa tokenwithRsa = new JwtTokenwithRsa();
			tokenwithRsa.generateJWTWithRsa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
