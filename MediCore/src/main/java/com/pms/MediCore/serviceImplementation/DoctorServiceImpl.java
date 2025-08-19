package com.pms.MediCore.serviceImplementation;

import com.pms.MediCore.dto.request.DoctorRequest;
import com.pms.MediCore.dto.response.DoctorResponse;
import com.pms.MediCore.dto.request.DoctorSearchRequest;
import com.pms.MediCore.dto.response.DoctorSearchResponse;
import com.pms.MediCore.entity.Doctor;
import com.pms.MediCore.repository.DoctorRepository;
import com.pms.MediCore.repository.DoctorSearchRepository;
import com.pms.MediCore.service.DoctorService;
import com.pms.MediCore.view.DoctorSearch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final DoctorSearchRepository doctorSearchRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper, DoctorSearchRepository doctorSearchRepository) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.doctorSearchRepository = doctorSearchRepository;
    }

    @Override
     public DoctorRequest addDoctor( DoctorRequest doctorRequest){
        try {
            Doctor doctor = modelMapper.map(doctorRequest, Doctor.class);
            doctor.setLastModifiedDate(new Date());
            Doctor savedDoctor = doctorRepository.save(doctor);
            return modelMapper.map(savedDoctor, DoctorRequest.class);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DoctorResponse> getAllDoctors(){
        List<Doctor> doctors= doctorRepository.findByActive(1L);

        return doctors.stream().map(doctor -> modelMapper.map(doctor, DoctorResponse.class)).collect(Collectors.toList());
    }

    @Override
    public DoctorResponse getDoctorById(Long id){
        try {
            Doctor doctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Doctor is not found"));

            return modelMapper.map(doctor, DoctorResponse.class);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Doctor is not found");
        }
    }

    @Override
    public DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long id){
        Doctor doctor=doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor is not found"));
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setDocId(doctorRequest.getDocId());
        doctor.setLastName(doctorRequest.getLastName());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setActive(doctorRequest.getActive());
        doctor.setAge(doctorRequest.getAge());
        doctor.setState(doctorRequest.getState());
        doctor.setGender(doctorRequest.getGender());
        doctor.setStatus(doctorRequest.getStatus());
        doctor.setWard(doctorRequest.getWard());
        doctor.setCategory(doctorRequest.getCategory());

        doctor.setLastModifiedDate(new Date());
        Doctor updatedDoctor=doctorRepository.save(doctor);
        return modelMapper.map(updatedDoctor, DoctorResponse.class);

    }
    @Override
    public void deleteDoctor(Long id){
        Doctor doctor=doctorRepository.findByIdAndActive(id, 1L).orElseThrow(()-> new RuntimeException("Doctor is not found"));
        doctor.setActive(2L);
        doctor.setLastModifiedDate(new Date());
        Doctor deletedDoctor=doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorSearchResponse> searchDoctor(DoctorSearchRequest doctorSearchRequest){
        try{

            List<DoctorSearch> doctor= doctorSearchRepository.searchDoctorByFilter(
                    doctorSearchRequest.getFirstName()== null ? "" : doctorSearchRequest.getFirstName(),
                    doctorSearchRequest.getLastName()==null ? "" : doctorSearchRequest.getLastName(),
                    doctorSearchRequest.getCategoryId()==null? -2L : doctorSearchRequest.getCategoryId(),
                    doctorSearchRequest.getWardId() == null? -2L : doctorSearchRequest.getWardId(),
                    doctorSearchRequest.getActive()==null? -2L : doctorSearchRequest.getActive()
            );
             return doctor.stream()
                     .map(p->modelMapper.map(p,DoctorSearchResponse.class )).toList();
        }

        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DoctorResponse> findByDoctorByCategory(Long category, Long active){
        List<Doctor> doctor=doctorRepository.findByCategoryAndActive(category, 1L);
        return doctor.stream().map(d->modelMapper.map(d, DoctorResponse.class)).collect(Collectors.toList());
    }

}
