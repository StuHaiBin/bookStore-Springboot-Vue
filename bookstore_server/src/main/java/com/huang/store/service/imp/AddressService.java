package com.huang.store.service.imp;

import com.huang.store.entity.user.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    int addAddress(Address address);
    int deleteAddress(int id);
    int modifyAddress(Address address);
    List<Address> addressList(String account);
    int setAddressOff(int id);
}
