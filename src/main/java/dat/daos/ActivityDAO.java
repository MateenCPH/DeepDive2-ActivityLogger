package dat.daos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dat.dtos.ActivityDTO;
import dat.entities.Activity;
import dat.entities.CityInfo;
import dat.entities.WeatherInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ActivityDAO implements IDAO<Activity> {
    private final EntityManagerFactory emf;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ActivityDAO(EntityManagerFactory emf) {
        this.emf = emf;
        this.objectMapper.registerModule(new JavaTimeModule());
    }


    @Override
    public Activity create(Activity activity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Handle CityInfo persistence
            CityInfo cityInfo = activity.getCityInfo();
            if (cityInfo != null) {
                TypedQuery<CityInfo> cityQuery = em.createQuery("SELECT c FROM CityInfo c WHERE c.cityName = :cityName", CityInfo.class);
                cityQuery.setParameter("cityName", cityInfo.getCityName());

                try {
                    // Check if the city already exists in the DB
                    CityInfo foundCityInfo = cityQuery.getSingleResult();
                    activity.setCityInfo(foundCityInfo); // Use the existing CityInfo
                } catch (NoResultException e) {
                    // If city info is not found, persist the new one
                    em.persist(cityInfo);
                }
            }

            // Handle WeatherInfo persistence
            WeatherInfo weatherInfo = activity.getWeatherInfo();
            if (weatherInfo != null) {
                TypedQuery<WeatherInfo> weatherQuery = em.createQuery("SELECT w FROM WeatherInfo w WHERE w.locationName = :locationName", WeatherInfo.class);
                weatherQuery.setParameter("locationName", weatherInfo.getLocationName());

                try {
                    // Check if the weather info already exists in the DB
                    WeatherInfo foundWeatherInfo = weatherQuery.getSingleResult();
                    activity.setWeatherInfo(foundWeatherInfo); // Use the existing WeatherInfo
                } catch (NoResultException e) {
                    // If weather info is not found, persist the new one
                    em.persist(weatherInfo);
                }
            }

            // Persist the Activity (this will also persist CityInfo and WeatherInfo if new)
            em.persist(activity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return activity;
    }

    @Override
    public Activity update(Activity activity) {
        return null;
    }

    @Override
    public void delete(Activity activity) {

    }

    @Override
    public Activity getById(Long id) {
        return null;
    }

    @Override
    public Set<Activity> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Activity> query = em.createNamedQuery("Activity.getAll", Activity.class);
            return query.getResultStream().collect(Collectors.toSet());
        }
    }

    public String getAllAsJson() {
        Set<Activity> activities = getAll();
        List<ActivityDTO> activityDTOs = activities.stream()
                .map(ActivityDTO::new) //Convert each activity to its corresponding DTO
                .collect(Collectors.toList());
        try {
            return objectMapper.writeValueAsString(activityDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error converting activities to JSON";
        }
    }

    public Set<Activity> getActivityByDate(LocalDate date) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Activity> query = em.createNamedQuery("Activity.findByDate", Activity.class);
            query.setParameter("exerciseDate", date);
            return query.getResultStream().collect(Collectors.toSet());
        }
    }
}
