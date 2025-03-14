package com.example.tuyendung1.service.feign;

import com.example.tuyendung1.controller.feign.DepartmentClient;
import com.example.tuyendung1.dto.model.Department;
import com.example.tuyendung1.dto.model.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentClient departmentClient;
    private final String token = "Bearer eyJraWQiOiIzYTgyNmZjOC00OGRlLTRmZTYtOTllOC0wNDkzY2M2YzFlYzgiLCJhbGciOiJSUzI1NiJ9.eyJ0ZW5hbnRfaWQiOiIyMDAiLCJzdWIiOiJzeXN0ZW1fYWRtaW4iLCJpc19vd25lciI6ZmFsc2UsImlzcyI6Imh0dHA6Ly8xMC4wLjEuMjE3OjgwODEiLCJhdXRob3JpdGllcyI6WyIzMzMzMyIsInR0dCIsIlFMbHZlMSIsIlNZU1RFTV9BRE1JTiJdLCJhdWQiOiJ0ZXN0IiwibmJmIjoxNzQxNjYwMjI4LCJ1c2VyX2lkIjoxLCJvcmdfaWQiOjEsInNjb3BlIjpbImdhdGV3YXkiXSwiZXhwIjoxNzQyNTI0MjI4LCJpYXQiOjE3NDE2NjAyMjgsImp0aSI6ImY0MTA5ODAyLTE0MjEtNGJkOC1iYzFlLWViMmIzZTAwNDVkOCJ9.TvDPeCrwL391mLlzR_zHwoFHxi2vaCRDywX41jW1Vkszbm0-LpgNcCRdRBbF9qkrs10YCI3t0i1Eu3tJYIG_T8wgFbzjGP6NZD3YBS63YefkHJvQA1rZmhA_xmBlkdUXpsWJlCQL4zBBx2oQpLoK-SWpSr8jo0KE9hMY11UTilHk63qchOUPBoUOS3Eaol5iEThDooD-Jq-8sDLkC3l15orQvCCBFXe44eUzrUgTRCICCxToi21xO0_MadDeKlYP5ImA-6rayHI3WtNUswEKv63KMAxbHznTsVOsJr-m9_-ZUNmdyGlLAHjiw36RZNhZ1kP-GTwAsrH0d9b77pUjTw";

    public List<Department> getDepartment(List<Long> ids) {
        ResponseEntity<DepartmentResponse> departments = departmentClient
                .getDepartments(token, 200, ids, 0, 20);
        if (departments.getBody().getData().getContent()==null || departments.getBody().getData().getContent().isEmpty()) {
            throw new RuntimeException("Can't get department");
        }
        return departments.getBody().getData().getContent();
    }
}