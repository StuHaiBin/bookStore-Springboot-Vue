package com.huang.store.service;

import com.huang.store.entity.user.Address;
import com.huang.store.mapper.AddressMapper;
import com.huang.store.service.imp.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("firstAddress")
public class AddressServiceImp implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public int addAddress(Address address) {
        int result = addressMapper.addAddress(address);
        return result;
    }

    @Override
    public int deleteAddress(int id) {
        int result = addressMapper.deleteAddress(id);
        return result;
    }

    @Override
    public int modifyAddress(Address address) {
        int result = addressMapper.modifyAddress(address);
        return result;
    }

    @Override
    public List<Address> addressList(String account) {
        return addressMapper.addressList(account);
    }

    @Override
    public int setAddressOff(int id) {
        int result = addressMapper.setAddressOff(id);
        return result;
    }
}
