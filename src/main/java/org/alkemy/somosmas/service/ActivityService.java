package org.alkemy.somosmas.service;

import org.alkemy.somosmas.model.Activity;

public interface ActivityService {

    Activity create(Activity activity);

    Activity update(Long id, Activity activity, String fileType) throws Exception;

}
