package com.katalon.kata.sample.utils;

import com.katalon.kata.sample.constans.Constants;
import com.katalon.kata.sample.reporter.EstadoPrueba;
import com.katalon.kata.sample.reporter.ImedReports;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.katalon.kata.sample.constans.Constants.AMBIENTE;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By.ByTagName;
import java.math.BigDecimal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Utils {

    public static String tipoAmbiente(){
        if(AMBIENTE.equals("QA")){
            return "Certificación";
        }else if(AMBIENTE.equals("INT")){
            return "Integración";
        }else{
            return "Desarrollo";
        }

    }

    public static boolean isEnabled(WebElement element) throws NoSuchElementException {
        System.out.println("Esta el elemento habilitado?:" + element.getAttribute("enabled"));
        return element.getAttribute("enabled").trim().equals("true");
    }

    public static void generaXmlConfirmacion( String nombreArchivo ) {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = docBuilder.newDocument();
        Element rootElement = ((org.w3c.dom.Document) doc).createElement("MsgInput");
        doc.appendChild(rootElement);
        String rutaArchivos = Constants.pathArchivos.concat("\\Data Files\\");
        System.out.println(">>>>> ruta de archivos "+rutaArchivos);

        try {

            FileInputStream file = new FileInputStream(new File( rutaArchivos + nombreArchivo +".xlsx"));
            //FileInputStream file = new FileInputStream(new File( utils.VariablesGlobales.pathArchivos + nombreArchivo +".xlsx"));
            XSSFWorkbook workbook1 = new XSSFWorkbook(file);
            XSSFSheet sheet1 = workbook1.getSheetAt(0);

            XSSFCell extCodFinanciador = sheet1.getRow(0).getCell(0);
            XSSFCell value_extCodFinanciador = sheet1.getRow(1).getCell(0);
            XSSFCell extCodSeguro = sheet1.getRow(0).getCell(1);
            XSSFCell value_extCodSeguro = sheet1.getRow(1).getCell(1);
            XSSFCell extCodLugar = sheet1.getRow(0).getCell(2);
            XSSFCell value_extCodLugar = sheet1.getRow(1).getCell(2);
            XSSFCell extFolioBono = sheet1.getRow(0).getCell(3);
            XSSFCell value_extFolioBono = sheet1.getRow(1).getCell(3);
            XSSFCell extNumOperacion = sheet1.getRow(0).getCell(4);
            XSSFCell value_extNumOperacion = sheet1.getRow(1).getCell(4);
            XSSFCell ExtMtoTot = sheet1.getRow(0).getCell(5);
            XSSFCell value_ExtMtoTot = sheet1.getRow(1).getCell(5);
            XSSFCell ExtMtoCopago = sheet1.getRow(0).getCell(6);
            XSSFCell value_ExtMtoCopago = sheet1.getRow(1).getCell(6);
            XSSFCell ExtMtoBonif = sheet1.getRow(0).getCell(7);
            XSSFCell value_ExtMtoBonif = sheet1.getRow(1).getCell(7);
            XSSFCell extFechaEmision = sheet1.getRow(0).getCell(8);
            XSSFCell value_extFechaEmision = sheet1.getRow(1).getCell(8);
            XSSFCell extRutBeneficiario = sheet1.getRow(0).getCell(9);
            XSSFCell value_extRutBeneficiario = sheet1.getRow(1).getCell(9);
            XSSFCell extRutCajero = sheet1.getRow(0).getCell(10);
            XSSFCell value_extRutCajero = sheet1.getRow(1).getCell(10);
            XSSFCell extRutPrestador = sheet1.getRow(0).getCell(11);
            XSSFCell value_extRutPrestador = sheet1.getRow(1).getCell(11);
            XSSFCell extRutEmisor = sheet1.getRow(0).getCell(12);
            XSSFCell value_extRutEmisor = sheet1.getRow(1).getCell(12);
            XSSFCell extLisPrest = sheet1.getRow(0).getCell(13);
            XSSFCell value_extLisPrest = sheet1.getRow(1).getCell(13);

            //parsear valores
            String cast_extCodFinanciador = value_extCodFinanciador.toString().replace( ".0", "" );
            String cast_extCodSeguro = value_extCodSeguro.toString().replace( ".0", "" );
            String cast_extCodLugar = value_extCodLugar.toString().replace( ".0", "" );
            //String cast_extFolioBono = value_extFolioBono.toString().replace( ".0", "" );
            BigDecimal cast_extFolioBono = new BigDecimal( String.valueOf( value_extFolioBono ) );
            int cast_extNumOperacion = new BigDecimal( String.valueOf( value_extNumOperacion ) ).intValue();
            String cast_ExtMtoTot = value_ExtMtoTot.toString().replace( ".0", "" );
            String cast_ExtMtoCopago = value_ExtMtoCopago.toString().replace( ".0", "" );
            String cast_ExtMtoBonif = value_ExtMtoBonif.toString().replace( ".0", "" );
            String cast_extFechaEmision = new BigDecimal( String.valueOf( value_extFechaEmision ) ).toString();

            // crear nodos xml
            Element nombre = (doc).createElement( String.valueOf( extCodFinanciador ) );
            nombre.appendChild( ((org.w3c.dom.Document) doc).createTextNode( ( cast_extCodFinanciador ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement(String.valueOf( extCodSeguro ));
            nombre.appendChild( (doc).createTextNode((cast_extCodSeguro)));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement(String.valueOf(extCodLugar));
            nombre.appendChild( (doc).createTextNode((cast_extCodLugar)));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement(String.valueOf(extFolioBono));
            nombre.appendChild( (doc).createTextNode( String.valueOf( cast_extFolioBono ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement(String.valueOf(extNumOperacion));
            nombre.appendChild( (doc).createTextNode( String.valueOf( cast_extNumOperacion ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( ExtMtoTot ) );
            nombre.appendChild( (doc).createTextNode(cast_ExtMtoTot));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( ExtMtoCopago ) );
            nombre.appendChild( (doc).createTextNode(cast_ExtMtoCopago));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( ExtMtoBonif ) );
            nombre.appendChild( (doc).createTextNode(cast_ExtMtoBonif));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extFechaEmision ) );
            nombre.appendChild( ((org.w3c.dom.Document) doc).createTextNode(cast_extFechaEmision) );
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extRutBeneficiario ) );
            nombre.appendChild( (doc).createTextNode( String.valueOf( value_extRutBeneficiario ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extRutCajero ) );
            nombre.appendChild( ( doc).createTextNode( String.valueOf( value_extRutCajero ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extRutPrestador ) );
            nombre.appendChild( (doc).createTextNode( String.valueOf( value_extRutPrestador ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extRutEmisor ) );
            nombre.appendChild( (doc).createTextNode( String.valueOf( value_extRutBeneficiario ) ));
            rootElement.appendChild( nombre );

            nombre = (doc).createElement( String.valueOf( extLisPrest ) );
            nombre.appendChild( (doc).createTextNode( String.valueOf( value_extLisPrest ) ));
            rootElement.appendChild( nombre );


            // escribimos el contenido en un archivo .xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource( doc );
            StreamResult result = new StreamResult(new File( rutaArchivos + nombreArchivo +".xml"));
            transformer.transform(source, result);
            System.out.println("Archivo xml creado!");

            // Cerrar el archivo

            //'Read data from excel'
            int Data_fromCell = (int) sheet1.getRow(1).getCell(4).getNumericCellValue();
            //String valor = sheet1.getRow(1).getCell(4).getNumericCellValue();
            //String valor = String.valueOf( sheet1.getRow(1).getCell(4).getStringCellValue());
            int cast_valor = Integer.parseInt( String.valueOf( Data_fromCell ) );
            System.out.println(">>> valor "+Data_fromCell);

            //'Write data to excel'
            Data_fromCell++ ;
            //cast_valor = String.valueOf( cast_valor );
            System.out.println (">>> nuevo valor "+Data_fromCell);
            sheet1.getRow(1).createCell(4).setCellValue( Data_fromCell );

            file.close();
            FileOutputStream outFile =new FileOutputStream(new File( rutaArchivos + nombreArchivo +".xlsx"));
            workbook1.write(outFile);
            outFile.close();
            System.out.println("Archivo excel actualizado");

        }catch (Exception file){
            System.out.println("Error con el archivo: "+file);
        }

    }

    public static void leerExcel(String nombreArchivo) throws TransformerException, ParserConfigurationException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        ImedReports.addReport ("XML_Create",nombreArchivo,EstadoPrueba.PASSED,false);

        Element rootElement = ((org.w3c.dom.Document) doc).createElement("x:Envelope");
        doc.appendChild(rootElement);

        Element Header = ((org.w3c.dom.Document) doc).createElement("x:Header");
        rootElement.appendChild(Header);

        Element Body = ((org.w3c.dom.Document) doc).createElement("x:Body");
        rootElement.appendChild(Body);

        Element Create = ((org.w3c.dom.Document) doc).createElement("urn:Create");
        Body.appendChild(Create);

        Element Auth = ((org.w3c.dom.Document) doc).createElement("urn:Auth");
        Create.appendChild(Auth);


        FileInputStream file = new FileInputStream(new File (Constants.pathArchivos + nombreArchivo+ ".xlsx"));
        XSSFWorkbook workbook1 = new XSSFWorkbook (file);
        XSSFSheet sheet1 = workbook1.getSheetAt(0);


        String SecretToken =String.valueOf (sheet1.getRow(0).getCell(0));
        String value_SecretToken = String.valueOf (sheet1.getRow(1).getCell(0));
        System.out.println ( ">>> row "+SecretToken  +"-"+value_SecretToken);

        ImedReports.addReport ("leerExecelvalue_SecretToken",value_SecretToken,EstadoPrueba.PASSED,false);


        String SecretKey = String.valueOf(sheet1.getRow(0).getCell(1));
        String value_SecretKey = String.valueOf(sheet1.getRow(1).getCell(1));
        ImedReports.addReport ("leerExecelvalue_value_SecretKey",value_SecretKey,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+SecretKey  +"-"+value_SecretKey);

        String Name = String.valueOf(sheet1.getRow(0).getCell(2));

        String value_Name = String.valueOf(sheet1.getRow(1).getCell(2));
        ImedReports.addReport ("leerExecelvalue_value_Name",value_Name,EstadoPrueba.PASSED,false);
        System.out.println ( ">>> row "+Name  +"-"+value_Name);

        String Institution = String.valueOf(sheet1.getRow(0).getCell(3));

        String value_Institution = String.valueOf(sheet1.getRow(1).getCell(3));
        ImedReports.addReport ("leerExecelvalue_value_Institution",value_Institution,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+Institution  +"-"+value_Institution);

        String DocType = String.valueOf(sheet1.getRow(0).getCell(4));

        String value_DocType = String.valueOf(sheet1.getRow(1).getCell(4));
        ImedReports.addReport ("leerExecelvalue_value_DocType",value_DocType,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+DocType  +"-"+value_DocType);

        String Md5 = String.valueOf(sheet1.getRow(0).getCell(5));

        String value_Md5 = String.valueOf(sheet1.getRow(1).getCell(5));
        ImedReports.addReport ("leerExecelvalue_value_Md5",value_Md5,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+Md5  +"-"+value_Md5);

        String MimeType = String.valueOf(sheet1.getRow(0).getCell(6));

        String value_MimeType = String.valueOf(sheet1.getRow(1).getCell(6));
        ImedReports.addReport ("leerExecelvalue_value_MimeType",value_MimeType,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+MimeType  +"-"+value_MimeType);

        String Size = String.valueOf(sheet1.getRow(0).getCell(7));

        String value_Size = String.valueOf(sheet1.getRow(1).getCell(7));
        ImedReports.addReport ("leerExecelvalue_value_Size",value_Size,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+Size  +"-"+value_Size);

        String pdf = String.valueOf(sheet1.getRow(0).getCell(8));

        String value_pdf = String.valueOf(sheet1.getRow(1).getCell(8));
        ImedReports.addReport ("leerExecelvalue_value_pdf",value_pdf,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+pdf  +"-"+value_pdf);

        String signers = String.valueOf(sheet1.getRow(0).getCell(9));

        String value_signers = String.valueOf(sheet1.getRow(1).getCell(9));
        ImedReports.addReport ("leerExecelvalue_signers",value_signers,EstadoPrueba.PASSED,false);
        System.out.println (">>> row "+signers  +"-"+value_signers);

        Element elemSecretToken = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretToken);
        elemSecretToken.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretToken));
        Auth.appendChild( elemSecretToken );

        Element elemSecretKey = ((org.w3c.dom.Document) doc).createElement("urn:"+SecretKey);
        elemSecretKey.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_SecretKey));
        Auth.appendChild( elemSecretKey );

        Element elemName = ((org.w3c.dom.Document) doc).createElement("urn:"+Name);
        elemName.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Name));
        Create.appendChild( elemName );


        Element elemInstitution = ((org.w3c.dom.Document) doc).createElement("urn:"+Institution);
        elemInstitution.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Institution));
        Create.appendChild( elemInstitution );

        Element elemDocType = ((org.w3c.dom.Document) doc).createElement("urn:"+DocType);
        elemDocType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_DocType));
        Create.appendChild( elemDocType );

        Element elemMd5 = ((org.w3c.dom.Document) doc).createElement("urn:"+Md5);
        elemMd5.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Md5));
        Create.appendChild( elemMd5 );

        Element elemMimeType = ((org.w3c.dom.Document) doc).createElement("urn:"+MimeType);
        elemMimeType.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_MimeType));
        Create.appendChild( elemMimeType );

        Element elemSize = ((org.w3c.dom.Document) doc).createElement("urn:"+Size);
        elemSize.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_Size));
        Create.appendChild( elemSize );

        Element elempdf = ((org.w3c.dom.Document) doc).createElement("urn:"+pdf);
        elempdf.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_pdf));
        Create.appendChild( elempdf );

        Element elemsigners = ((org.w3c.dom.Document) doc).createElement("urn:"+signers);
        elemsigners.appendChild( ((org.w3c.dom.Document) doc).createTextNode(value_signers));
        Create.appendChild( elemsigners );

        // escribimos el contenido en un archivo .xml
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer ();
        DOMSource source = new DOMSource( doc );

        StreamResult result = new StreamResult(new File(Constants.pathArchivos + nombreArchivo + ".xml"));
        transformer.transform(source, result);
        System.out.println("archivo generado!");

    }

    /*
    public static String obtenerResponse(String urlRequest, String archivoXml) throws IOException {

        File xmlFile = new File( Constants.pathArchivos + archivoXml +".xml");
        Reader fileReader = new FileReader (xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder(); String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n"); line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        bufReader.close();
        xml2String = xml2String.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

        try {
            //String url = "http://liquidador3qa.i-med.cl/wsImed/wsConfirmacion.asmx";
            String url = urlRequest;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");

            String xml;


            xml = xml2String;


            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            //System.out.println(responseStatus);
            BufferedReader res = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = res.readLine()) != null) {
                response.append(inputLine);
            }

            //System.out.println("response:" + response.toString());
            res.close();
            //System.out.println("response:" + response.toString());
            return response.toString();

        } catch (Exception e) {
            System.out.println(e);
        }

        return "";

    }
    */
    public static String obtenerResponse( String urlRequest, String archivoXml) throws IOException {

        String rutaArchivos = Constants.pathArchivos.concat("\\Data Files\\");
        File xmlFile = new File( rutaArchivos + archivoXml +".xml");
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder(); String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n"); line = bufReader.readLine();
        }
        String xml2String = sb.toString();
        bufReader.close();

        String[] str = new String[2];

        xml2String = xml2String.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();

        try {
            //String url = "http://liquidador3qa.i-med.cl/wsImed/wsConfirmacion.asmx";
            String url = urlRequest;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");

            String xml="";
            if( (archivoXml.contains("confirmacion")) || (archivoXml.contains("certificacion"))  ){
                //println(">>> SI")
                xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                        " <soapenv:Body>\n" +
                        "   <tem:wmImed_SrvConfirmacion xmlns=\"http://tempuri.org/\">\n" +
                        "     <tem:XML_INPUT><![CDATA[\n" +
                        xml2String+
                        "]]>\n" +
                        "\t</tem:XML_INPUT>\n" +
                        "</tem:wmImed_SrvConfirmacion>\n" +
                        " </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

            }else if ( (archivoXml.contains("medsign")) ){
                xml = xml2String;
            }

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();

            String responseStatus = con.getResponseMessage();
            String codRespuesta = String.valueOf( con.getResponseCode() );
            String separador = "@";
            codRespuesta += separador;
            //System.out.println(responseStatus);
            BufferedReader res = new BufferedReader(new InputStreamReader( con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = res.readLine()) != null) {
                response.append(inputLine);
            }


            return  ( (codRespuesta.toString()) + (response.toString()));
            //return response.toString();


        } catch (Exception e) {
            System.out.println(e);
        }

        return "";

    }

    public static String buscarTag(String respuesta, String tagRaiz, String tagNameBuscar){

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(respuesta));
        Document doc = null;
        try {
            doc = documentBuilder.parse(inputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(">>> metodo "+dostor.getElementsByTagName(tagRaiz).getLength());
        String tagResponse="";
        NodeList tagName = doc.getElementsByTagName(tagRaiz);
        for ( int i = 0; i < tagName.getLength(); i++){
            NodeList Code = ((Element)tagName.item(i)).getElementsByTagName(tagNameBuscar);
            //NodeList Description = ((Element)tagName.item(i)).getElementsByTagName("Description");
            //System.out.println(tagNameBuscar +" : "+Code.item(i).getTextContent());
            tagResponse = Code.item(i).getTextContent();
        }
        return tagResponse;
    }

    public static String buscarTag(String respuesta, String tagNameBuscar){

        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(respuesta));
        Document dostor = null;
        try {
            dostor = documentBuilder.parse(inputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>> metodo "+dostor.getElementsByTagName(tagNameBuscar).getLength());
        NodeList tagName = dostor.getElementsByTagName(tagNameBuscar);

        System.out.println(tagNameBuscar +" : "+tagName.item(0).getTextContent());
        String url = tagName.item(0).getTextContent();

        return url;

    }





}
