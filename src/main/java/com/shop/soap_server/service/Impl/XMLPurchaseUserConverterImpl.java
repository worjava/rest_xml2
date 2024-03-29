package com.shop.soap_server.service.Impl;

import com.shop.soap_server.exception.ValidationException;
import com.shop.soap_server.mapper.PurchaseInfoMapper;
import com.shop.soap_server.mapper.UserMapper;
import com.shop.soap_server.model.PurchaseInfo;

import com.shop.soap_server.model.User;

import com.shop.soap_server.model.dto.UserPurchaseDto;
import com.shop.soap_server.model.wrapperXML.PurchasesUsersWrapper;
import com.shop.soap_server.service.XMLPurchaseUserConverter;
import com.shop.soap_server.service.XMLValidatorService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class XMLPurchaseUserConverterImpl implements XMLPurchaseUserConverter {
    public XMLPurchaseUserConverterImpl(XMLValidatorService xmlValidatorService) {
        this.xmlValidatorService = xmlValidatorService;
    }

    private final XMLValidatorService xmlValidatorService;

    @Override
    public PurchaseInfo convertToPurchaseInfo(String purchaseXml) {
        try {
            if (xmlValidatorService.isValid(purchaseXml)) {
                throw new ValidationException("Покупка не обновлена. Ошибка Валидации");
            }
            UserPurchaseDto dto = parsXMLToUserOrPurchaseDto(purchaseXml);
            return PurchaseInfoMapper.INSTANCE.dtoToEntity(dto.getPurchaseInfo());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ValidationException("Ошибка XML: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Ошибка конфигурации парсера XML", e);
        }
    }


    @Override
    public User convertTo_User(String userXML) {
        try {
            if (xmlValidatorService.isValid(userXML)) {
                throw new ValidationException("Покупка не обновлена. Ошибка Валидации");
            }

            UserPurchaseDto dto = parsXMLToUserOrPurchaseDto(userXML);

            return UserMapper.INSTANCE.dtoToEntity(dto.getUser());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ValidationException("Ошибка XML: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Ошибка конфигурации парсера XML", e);
        }
    }

    private UserPurchaseDto parsXMLToUserOrPurchaseDto(String xmlData) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(UserPurchaseDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xmlData);
        return (UserPurchaseDto) unmarshaller.unmarshal(reader);
    }

    public  <T> String convertToXML( T object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }

}
