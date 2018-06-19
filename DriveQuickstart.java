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

    
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CLIENT_SECRET_DIR = "client_secret.json";


    
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
  public static void guardar(String nombre) throws IOException, GeneralSecurityException{
	  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
   
      Drive service2 = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
              .setApplicationName(APPLICATION_NAME)
              .build();
      
      String folderId = "1tzQYR-OAW3BagwhJrtEHpVSyrwZx4c34";
      File fileMetadata = new File();
      fileMetadata.setName(nombre+".pdf");
      fileMetadata.setParents(Collections.singletonList(folderId));
      java.io.File filePath = new java.io.File("E:\\"+nombre+".pdf");
      FileContent mediaContent = new FileContent("doc/pdf", filePath);
        try {
		
		File file = service2.files().create(fileMetadata, mediaContent)
          .setFields("id, parents")
          .execute();
      System.out.println("File ID: " + file.getId());
      } catch (IOException e) {
          System.out.println("An error occurred: " + e);
        }
	  
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
  
    public static void main(String... args) throws IOException, GeneralSecurityException {
    	String[]arr = new String [10000];
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        Drive service2 = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
        	int i=0;
            System.out.println("Files:");
            for (File file : files) {
            	arr[i]=file.getName();
            	i++;
            	arr[i]=file.getId();
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
                i++;
            }
        }
        
        /*  String folderId = "1tzQYR-OAW3BagwhJrtEHpVSyrwZx4c34";
        File fileMetadata = new File();
        fileMetadata.setName("1.png");
        fileMetadata.setParents(Collections.singletonList(folderId));
        java.io.File filePath = new java.io.File("E:\\1.png");
        FileContent mediaContent = new FileContent("image/jpeg", filePath);
          try {
		
		File file = service2.files().create(fileMetadata, mediaContent)
            .setFields("id, parents")
            .execute();
        System.out.println("File ID: " + file.getId());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
          }*/
        for(int i=0;i<arr.length;i++) {
        	System.out.println(arr[i]);
        }
       while(true) {
    	   Scanner scan = new Scanner(System.in);
    	   System.out.println("Que accion desea hacer");
    	   System.out.println("1. Guardar archivo");
    	   System.out.println("2. Descargar archivo");
    	   int eleccion = scan.nextInt();
    	   if(eleccion==1) {
    		   Scanner scan2 = new Scanner(System.in);
    		   System.out.println("Ingrese el nombre del archivo a guardar");
    		   String nombre = scan2.nextLine();
    		   guardar(nombre);
    	   }
    	   if(eleccion==2) {
    		   Scanner scan2 = new Scanner(System.in);
    		   System.out.println("Ingrese el nombre del archivo a descargar");
    		   String nombre = scan2.nextLine();
    		   
    		   for(int i=0;i<arr.length;i++) {
    			   if(arr[i].equals(nombre)) {
    				   descargar(arr[i],arr[i+1]);
    				   
    				   break;
    			   }
    		   }
    		   abrir(nombre);
    	   }
       }
       
    }
}
