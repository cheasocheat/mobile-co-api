package com.khmersolution.moduler.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.quotation.QuotationDocument;
import com.khmersolution.moduler.domain.response.document.QDocument;
import com.khmersolution.moduler.util.AppConfigFile;
import com.khmersolution.moduler.util.UploadUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

import static org.springframework.util.MimeTypeUtils.generateMultipartBoundary;

/**
 * Created by DANG DIM
 * Date     : 3/24/2018, 11:52 AM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DocumentController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public String getDocumentGroupByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                             @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/documents/list_document", HttpMethod.POST, entity, String.class).getBody();
        } else {
            return restTemplate.toString();
        }

    }

    @RequestMapping(value = "/documentgroups", method = RequestMethod.GET)
    public String getDocumentByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                        @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/documents/list_group", HttpMethod.POST, entity, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }


    @RequestMapping(value = "/quotation-documents", method = RequestMethod.GET)
    public String getQuotationDocumentByTemplate(
            @RequestParam(value = "lastUpdate", required = false) String lastUpdate,
            @RequestParam(value = "quota_id", required = true) Long quota_id,
            @RequestParam(value = "product", required = true) String product) {

        QDocument qdocument = new QDocument();
        qdocument.setQuotationId(quota_id);
        qdocument.setDate(lastUpdate);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<QDocument> request = new HttpEntity<>(qdocument, headers);

        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotation-documents/list", HttpMethod.POST, request, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }

    @ApiOperation(
            httpMethod = "POST",
            value = "Submit Quotation and images",
            notes = "The client have to submit json product and images using form data.",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/quotation-documents", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String submitQuotationDocument(@RequestPart(required = false) MultipartFile[] files, @RequestPart(required = false) String json) {
        String response = null;
        try {

            QuotationDocument qdMapper = objectMapper.readValue(json, QuotationDocument.class);
           /* String home = AppConfigFile.getInstance().getValue("temp_folder");
            File tmp_folder = new File(home + "/" + "tmp");
            if (!tmp_folder.exists()) {
                tmp_folder.mkdir();
            }*/

            if (qdMapper.getProduct().equalsIgnoreCase(null) && qdMapper.getProduct().equalsIgnoreCase("")) {
                return "Product is required !";
            } else if (qdMapper.getDocumentId() == null && qdMapper.getDocumentId() > 0) {
                return "DocumentId is required !";
            } else if (qdMapper.getQuotaId() == null && qdMapper.getQuotaId() > 0) {
                return "QuotationId is required !";
            } else {
                byte[] boundary = generateMultipartBoundary();
                Map<String, String> bound_char = Collections.singletonMap("boundary", new String(boundary, "US-ASCII"));
                MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA, bound_char);
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                headers.setContentType(contentType);
                HttpEntity<QuotationDocument> request = new HttpEntity<>(qdMapper, headers);
                if (qdMapper.getProduct().equalsIgnoreCase("Hd")) {
                    response = restTemplate.exchange(Route.HD_BASE_URL + "/quotation-documents/submit", HttpMethod.POST, request, String.class).getBody();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /*@ApiOperation(
            httpMethod = "POST",
            value = "Submit Quotation and images",
            notes = "The client have to submit json product and images using form data.",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/quotation-documents", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String cool(MultipartFile[] files, @RequestPart(required = false) String json) {
        String response = null;
        try {

            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            QuotationDocument qdMapper = objectMapper.readValue(json, QuotationDocument.class);

            if (qdMapper.getProduct().equalsIgnoreCase(null) && qdMapper.getProduct().equalsIgnoreCase("")) {
                return "Product is required !";
            } else if (qdMapper.getDocumentId() == null && qdMapper.getDocumentId() > 0) {
                return "DocumentId is required !";
            } else if (qdMapper.getQuotaId() == null && qdMapper.getQuotaId() > 0) {
                return "QuotationId is required !";
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                HttpEntity<QuotationDocument> request = new HttpEntity<>(qdMapper, headers);
                if (qdMapper.getProduct().equalsIgnoreCase("Hd")) {
                    response = restTemplate.exchange(Route.HD_BASE_URL + "/quotation-documents/cool", HttpMethod.POST, request, String.class).getBody();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }*/

    /*private String doaction(@RequestBody QuotationDocument qdMapper) {
        String response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<QuotationDocument> request = new HttpEntity<>(qdMapper, headers);
        if (qdMapper.getProduct().equalsIgnoreCase("Hd")) {
            response = restTemplate.exchange(Route.HD_BASE_URL + "/quotation-documents/cool", HttpMethod.POST, request, String.class).getBody();
        }
        return response;
    }
*/

    @RequestMapping(value = "/cool", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String cooAction(@RequestPart(required = false) MultipartFile[] files, String json) {

        final String uri = Route.HD_BASE_URL + "/quotation-documents/submit";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        File[] directoryListing = null;
        FileSystemResource[] fsr = null;
        List<FileSystemResource> lfsr = new ArrayList<>();

        String home = AppConfigFile.getInstance().getValue("temp_folder");
        File tmp_folder = new File(home + "/" + "tmp");
        if (!tmp_folder.exists()) {
            tmp_folder.mkdir();
        }
        try {

            InputStream inputStream = null;
            if (files.length > 0) {
                for (MultipartFile file : files) {
                    //fileNames.add(file.getOriginalFilename());
                    String fileName = file.getOriginalFilename();
                    inputStream = file.getInputStream();
                    UploadUtil.writeToFile(inputStream, tmp_folder + File.separator + fileName);

                }
            }
            if (inputStream != null) {
                inputStream.close();
            }

            File dir = new File(tmp_folder.getAbsolutePath());
            directoryListing = dir.listFiles();
            //allFile.add(dir.listFiles());

            /*if (directoryListing != null) {
                listF.add(directoryListing);
            }*/

            FileSystemResource file = new FileSystemResource("C:\\Users\\d.dim\\Desktop\\img\\2.jpg");

            map.add("files", file);
            map.add("json", json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();

        try {
            byte[] boundary = generateMultipartBoundary();
            Map<String, String> bound_char = Collections.singletonMap("boundary", new String(boundary, "US-ASCII"));
            MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA, bound_char);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
            headers.setContentType(contentType);
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

            //ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
            HttpEntity<String> response = restTemplate.postForEntity(uri, request, String.class, headers);
            if (response.getBody() != null) {
                delete(tmp_folder);
            }
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


   /* public static void main(String[] args) throws IOException {
        byte[] boundary = generateMultipartBoundary();
        Map<String, String> parameters = Collections.singletonMap("boundary", new String(boundary, "US-ASCII"));
        MediaType contentType = new MediaType(MediaType.MULTIPART_FORM_DATA, parameters);
       *//* outputMessage.getHeaders().setContentType(contentType);
        writeParts(outputMessage.getBody(), parts, boundary);
        writeEnd(boundary, outputMessage.getBody());*//*


        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource value = new FileSystemResource(new File("C:\\Users\\d.dim\\Desktop\\img\\1.jpg"));
        map.add("file", value);
        map.add("ss", 234);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:8080/mobile/efinance/quotation-documents/cool", HttpMethod.POST, requestEntity, String.class);

    }*/

    // Delete tmp folder
    private void delete(File file) {
        boolean success = false;
        if (file.isDirectory()) {
            for (File deleteMe : Objects.requireNonNull(file.listFiles())) {
                delete(deleteMe);
            }
        }
        success = file.delete();
        if (success) {
            System.out.println(file.getAbsoluteFile() + " File tmp is Deleted");
        } else {
            System.out.println(file.getAbsoluteFile() + " File tmp is Deletion failed!!!");
        }
    }

}


















