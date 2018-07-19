package EnvioRepositorio;

import com.google.api.client.auth.oauth2.Credential;


import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;

import java.util.*;
import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class DriveQuickstart {
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CLIENT_SECRET_DIR = "client_secret.json";


    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If there is no client_secret.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = DriveQuickstart.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
   
   private static void abrir(String archivo) {
   //ruta del archivo en el pc
   String file = new String("E:\\programas\\eclipse-workspace\\repositorio_drive4\\"+archivo); 
    
  try{ 
    //definiendo la ruta en la propiedad file
    Runtime.getRuntime().exec("cmd /c start "+file);
      
    }catch(IOException e){
       e.printStackTrace();
    } 
   }
   //Recibira un string nombre el cual sera el nombre del archivo sin extension, ruta sera el directorio donde esta el "archivo nombre"
  public static String guardar(String nombre,String ruta) throws IOException, GeneralSecurityException{
	  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
   
      Drive service2 = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME)
              .build();
      File fileMetadata = new File();
      fileMetadata.setName(nombre+".xml");
      java.io.File filePath = new java.io.File(ruta);
      FileContent mediaContent = new FileContent("doc/xml", filePath);
      File file = service2.files().create(fileMetadata, mediaContent)
          .setFields("id")
          .execute();
      return file.getId();
  /*    String folderId = "1tzQYR-OAW3BagwhJrtEHpVSyrwZx4c34";
      File fileMetadata = new File();
      fileMetadata.setName(nombre+".xml");
      fileMetadata.setParents(Collections.singletonList(folderId));
      java.io.File filePath = new java.io.File("E:\\"+nombre+".xml");
      FileContent mediaContent = new FileContent("doc/xml", filePath);
        try {
		
		File file = service2.files().create(fileMetadata, mediaContent)
          .setFields("id, parents")
          .execute();
      System.out.println("File ID: " + file.getId());
      } catch (IOException e) {
          System.out.println("An error occurred: " + e);
        }*/
	  
  }
  public static void descargar(String nombre, String id) throws IOException, GeneralSecurityException{
	  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
   
      Drive service2 = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME)
              .build();
      
      //String fileId = "1OACUBSX7Jtw6vdGzRqN5850RlZzoUrKv";
      String fileId = id;
      OutputStream out = new FileOutputStream(nombre);
   
      service2.files().get(fileId).executeMediaAndDownloadTo(out);
    
	  
  }
  
    
}