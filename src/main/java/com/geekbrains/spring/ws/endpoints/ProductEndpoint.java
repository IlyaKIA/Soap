package com.geekbrains.spring.ws.endpoints;

import com.geekbrains.spring.ws.services.ProductService;
import com.geekbrains.spring.ws.soap.products.GetAllProductsRequest;
import com.geekbrains.spring.ws.soap.products.GetAllProductsResponse;
import com.geekbrains.spring.ws.soap.products.GetProductByTitleRequest;
import com.geekbrains.spring.ws.soap.products.GetProductsByTitleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";
    private final ProductService productService;

    /*
    Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.geekbrains.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByTitleRequest>
                    <f:title>Apple</f:title>
                </f:getProductByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
 */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductsByTitleResponse getProductByTitle(@RequestPayload GetProductByTitleRequest request) {
        GetProductsByTitleResponse response = new GetProductsByTitleResponse();
        response.setProduct(productService.getByTitle(request.getTitle()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
