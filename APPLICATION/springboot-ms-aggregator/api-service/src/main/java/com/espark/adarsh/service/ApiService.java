package com.espark.adarsh.service;


import com.espark.adarsh.bean.Address;
import com.espark.adarsh.bean.BeanInf;
import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AddressService addressService;

    public Map<String, String> getMessages() {
        HashMap<String, String> response = new HashMap<String, String>();
        this.addressService.getMessage(response);
        this.employeeService.getMessage(response);
        return response;
    }

    public ResponseBean getAddress(Long id) {
        return new ResponseBean(id, this.addressService.getAddress(id));
    }

    public ResponseBean getEmployee(Long id) {
        return new ResponseBean(id, this.employeeService.getEmployee(id));
    }

    public ResponseBean getData(Long id) {
        Address address = this.addressService.getAddress(id);
        Employee employee = this.employeeService.getEmployee(id);
        return new ResponseBean(id, address, employee);
    }


    public List<ResponseBean> getAllData() {
        List<Address> addressList = this.addressService.getAddress();
        List<Employee> employeeList = this.employeeService.getEmployee();
        return this.getAggregatedData(addressList, employeeList);
    }


    private List<ResponseBean> getAggregatedData(List<Address> addresses, List<Employee> employees) {
        List<ResponseBean> responseBeans = new LinkedList<>();
        List<BeanInf> combinedList = new LinkedList<>();
        if (!addresses.isEmpty()) {
            combinedList.addAll(addresses);
        }
        if (!employees.isEmpty()) {
            combinedList.addAll(employees);
        }

        Map<Long, List<BeanInf>> groupedData =
                combinedList
                        .stream()
                        .collect(Collectors.groupingBy(beanInf -> beanInf.getId()));

        groupedData
                .entrySet()
                .forEach(longListEntry -> {
                            responseBeans.add(this.getData(longListEntry.getKey(), longListEntry.getValue()));
                        }
                );
        return responseBeans;
    }

    private ResponseBean getData(Long id, List<BeanInf> list) {
        Address address = null;
        Employee employee = null;
        for (BeanInf beanInf : list) {
            if (beanInf.getType() == BeanInf.Type.ADDRESS) {
                address = (Address) beanInf;
            } else if (beanInf.getType() == BeanInf.Type.EMPLOYEE) {
                employee = (Employee) beanInf;
            }
        }
        return new ResponseBean(id, address, employee);
    }

}
