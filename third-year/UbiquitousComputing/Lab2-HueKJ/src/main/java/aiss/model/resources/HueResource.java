package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class HueResource {

	// https://discovery.meethue.com/
	// https://developers.meethue.com/develop/get-started-2/

	private static final String USER_NAME = "XXXXXXXXXXX";
	private static final String IP = "XXXXXXXXXXX";

	private static final String BULB1 = "8";
	private static final String BULB2 = "9";

	public Boolean changeColorHue(String color, String bulb)
			throws UnsupportedEncodingException, MalformedURLException {

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		SSLContext sc;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		ClientResource cr = null;
		boolean success = true;

		String bulbParsed;
		if (bulb.equals("bulb1")) {
			bulbParsed = BULB1;
		} else {
			bulbParsed = BULB2;
		}
		String uri = "https://" + IP + "/api/" + USER_NAME + "/lights/" + bulbParsed + "/state";
		System.out.println(uri);
		String colorAfter = "800";

		switch (color) {

		case "blue":
			colorAfter = "40000";
			break;
		case "red":
			colorAfter = "800";
			break;
		case "green":
			colorAfter = "15000";
			break;
		case "purple":
			colorAfter = "50000";
			break;
		case "orange":
			colorAfter = "6000";
			break;
		case "yellow":
			colorAfter = "9000";
			break;
		}

		try {

			cr = new ClientResource(uri);

			if (color.contentEquals("off")) {
				cr.put("{\"on\":false}");

			} else {
				System.out.println(cr);
				cr.put("{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":" + colorAfter + "}");
			}

		} catch (ResourceException re) {

			System.err.println("Error: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}

		return success;
	}

}
