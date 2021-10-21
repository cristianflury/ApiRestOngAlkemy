package org.alkemy.somosmas.seeder;

import lombok.AllArgsConstructor;
import org.alkemy.somosmas.model.Activity;
import org.alkemy.somosmas.model.Organization;
import org.alkemy.somosmas.repository.ActivityRepository;
import org.alkemy.somosmas.repository.OrganizationRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@AllArgsConstructor
@Component
public class ActivitySeeder {

    private final ActivityRepository activityRepository;
    private final OrganizationRepository organizationRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedActivityTable();
    }

    private void seedActivityTable() {
        List<String> activityNameList = activityName();
        List<String> activityContentList = activityContent();
        List<String> activityImageList = activityImage();

        loadActivityData(activityNameList, activityContentList, activityImageList, organizationsList());
    }

    public void loadActivityData(List<String> nameList, List<String> contentList, List<String> imageList, List<String> organizationsList) {
        for (String n : nameList) {
            Optional<Activity> searchedActivity = activityRepository.findByName(n);
            if (searchedActivity.isEmpty()) {
                int index = nameList.indexOf(n);
                createActivity(n, contentList.get(index), imageList.get(index), organizationsList);
            }
        }
    }

    private List<String> organizationsList(){
        return List.of("World Vision",
                "Medicos sin fronteras",
                "Greenpeace",
                "Awid",
                "Amnistía internacional");
    }

    private List<String> activityName() {
        return List.of(
                "Apoyo escolar para el nivel primario",
                "Apoyo escolar nivel secundaria",
                "Tutorias",
                "Encuentro semanal con tutores",
                "Actividad proyecto",
                "Ayudantías",
                "Acompañamiento escolar y familiar",
                "Beca estímulo",
                "Taller arte y cuentos",
                "Paseos recreativos y educativos"
        );
    }

    private List<String> activityContent() {
        return List.of(
                "Taller para ayudar a los alumnos con el material de la escuela, junto a una docente que les da clases de lengua y matemática con una planificación propia que armamos en Manos para nivelar a los niños y que vayan con más herramientas a la escuela.",
                "Aquí los jóvenes se presentan con el material que traen del colegio y una docente de la institución y un grupo de voluntarios los recibe para ayudarlos a estudiar o hacer la tarea.",
                "Es un programa destinado a jóvenes a partir del tercer año de secundaria, cuyo objetivo es garantizar su permanencia en la escuela y construir un proyecto de vida que da sentido al colegio.",
                "Individuales y grupales",
                "Los participantes del programa deben pensar una actividad relacionada a lo que quieren hacer una vez terminada la escuela y su tutor los acompaña en el proceso.",
                "Los que comienzan el 2do años del programa deben elegir una de las actividades que se realizan en la institución para acompañarla e ir conociendo como es el mundo laboral que les espera.",
                "Los tutores son encargados de articular con la familia y con las escuelas de los jóvenes para monitorear el estado de los tutoreados.",
                "Los jóvenes reciben una beca estímulo que es supervisada por los tutores. Actualmente se encuentran inscriptos en el programa 30 adolescentes.",
                "Taller literario y de manualidades que se realiza semanalmente.",
                "Estos paseos están pensados para promover la participación y sentido de pertenencia de los niños, niñas y adolescentes al área educativa."
        );
    }

    private List<String> activityImage() {
        return List.of(
                "https://i0.wp.com/somosmas.org/wp-content/uploads/2015/02/9472332071_fd6d9aee63_z.jpg?resize=400%2C284&ssl=1",
                "https://i0.wp.com/somosmas.org/wp-content/uploads/2018/02/etv1.jpg?resize=400%2C284&ssl=1",
                "https://i2.wp.com/somosmas.org/wp-content/uploads/2014/12/HITO-13-.jpg?resize=400%2C284&ssl=1",
                "https://i1.wp.com/somosmas.org/wp-content/uploads/2018/05/emprende.jpg?resize=400%2C284&ssl=1",
                "https://i1.wp.com/somosmas.org/wp-content/uploads/2018/02/IMG_20171202_102724-e1519332772543.jpg?resize=400%2C284&ssl=1",
                "https://i1.wp.com/somosmas.org/wp-content/uploads/2015/02/CCI-J%C3%B3venes-Rurales-A088.jpg?resize=400%2C284&ssl=1",
                "https://i1.wp.com/somosmas.org/wp-content/uploads/2015/02/WP_20150801_013.jpg?resize=1080%2C606&ssl=1",
                "https://i2.wp.com/somosmas.org/wp-content/uploads/2015/08/NotICBF1.jpg?resize=1080%2C715&ssl=1",
                "https://i1.wp.com/somosmas.org/wp-content/uploads/2018/09/IMG_6645.jpg?resize=1080%2C810&ssl=1",
                "https://i0.wp.com/somosmas.org/wp-content/uploads/2017/03/IMG_5696.jpg?w=900&ssl=1"
        );
    }

    private void createActivity(String name, String content, String image, List<String> organizationsList) {
        String organizationName = getOrganization(organizationsList);
        Activity activity = new Activity();
        activity.setName(name);
        activity.setContent(content);
        activity.setImage(image);
        activity.setDeleted(false);
        activity.setOrganization(organizationRepository.existsByName(organizationName)
                ? organizationRepository.findByName(organizationName) : createOrganization(organizationName));
        activityRepository.save(activity);
    }

    private String getOrganization(List<String> list){
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(5);
        return list.get(index);

    }

    private Organization createOrganization(String organizationName){
        Organization organization = new Organization();

        organization.setName(organizationName);
        organization.setDeleted(false);
        organizationRepository.save(organization);
        return organization;
    }
}
