package com.khmersolution.moduler.web.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.quotation.QuotationDocument;
import com.khmersolution.moduler.domain.response.document.QDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

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

    @RequestMapping(value = "/quotation-documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String submitQuotationDocument(String json, MultipartFile[] files) throws IOException {
        String response = null;
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        QuotationDocument quotationDocument = objectMapper.readValue(json, QuotationDocument.class);
        if (quotationDocument.getQuotaId() == null && quotationDocument.getQuotaId() < 0) {
            return "quotation_id is require !";
        }
        if (quotationDocument.getDocumentId() == null && quotationDocument.getDocumentId() < 0) {
            return "document_id is require !";
        }
        if (quotationDocument.getProduct() == null && quotationDocument.getProduct().equals("")) {
            return "product type is require !";
        }
        if (quotationDocument.getProduct().equalsIgnoreCase("HD")) {
            MultiValueMap<String, Object> data = new LinkedMultiValueMap();
            data.add("json", json);
            for (MultipartFile file : files) {
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                data.add("files", resource);
            }
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(data, requestHeaders);
            response = restTemplate.postForObject(Route.HD_BASE_URL + "/quotation-documents/submit", requestEntity, String.class);
        }
        return response;
    }

    @RequestMapping(value = "/quotation-documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.PUT)
    public String updateQuotationDocument(@RequestPart(required = false) String json, MultipartFile[] files) throws IOException {
        String response = null;
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        QuotationDocument quotationDocument = objectMapper.readValue(json, QuotationDocument.class);
        if (quotationDocument.getQuotaId() == null && quotationDocument.getQuotaId() < 0) {
            return "quotation_id is require !";
        }
        if (quotationDocument.getDocumentId() == null && quotationDocument.getDocumentId() < 0) {
            return "document_id is require !";
        }
        if (quotationDocument.getProduct() == null && quotationDocument.getProduct().equals("")) {
            return "product type is require !";
        }
        if (quotationDocument.getProduct().equalsIgnoreCase("HD")) {
            MultiValueMap<String, Object> data = new LinkedMultiValueMap();
            data.add("json", json);
            for (MultipartFile file : files) {
                ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };
                data.add("files", resource);
            }
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(data, requestHeaders);
            response = restTemplate.postForObject(Route.HD_BASE_URL + "/quotation-documents/submit_update", requestEntity, String.class);
        }
        return response;
    }

}


















