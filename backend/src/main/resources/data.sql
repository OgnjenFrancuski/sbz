-- AUTHORITY
INSERT INTO public.authority(authority_id, authority_name)
VALUES (-1, 'ROLE_ADMIN');

INSERT INTO public.authority(authority_id, authority_name)
VALUES (-2, 'ROLE_PHYSICIAN');


-- PERSON
INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-1, 'Doktor', 'Doktoric', '123321');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-2, 'Lekar', 'Lekic', '234432');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-3, 'Mag', 'Magic', '345543');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-4, 'Pera', 'Peric', '567765');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-5, 'Zika', 'Zikic', '678876');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-6, 'Joca', 'Jocic', '789987');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-7, 'Mile', 'Milic', '890098');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-8, 'First Chronic Test', 'Six Sinus Diagnoses', 'ctf1');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-9, 'Second Chronic Test', 'Six Flu Diagnoses', 'ctf2');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-10, 'Addict', 'Test', 'adst');

INSERT INTO public.person(person_id, person_first_name, person_last_name, person_personal_id)
VALUES (-11, 'Weak Immune', 'System Test', 'wist');


-- PHYSICIAN
INSERT INTO public.physician(person_id)
VALUES (-1);

INSERT INTO public.physician(person_id)
VALUES (-2);

INSERT INTO public.physician(person_id)
VALUES (-3);


-- PATIENTS
INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-4, '123');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-5, '234');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-6, '345');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-7, '456');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-8, '567');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-9, '678');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-10, '789');

INSERT INTO public.patient(person_id, patient_health_card_id)
VALUES (-11, '890');


-- ACCOUNT
-- pass: admin
INSERT INTO public.account(account_id, account_username, account_password, account_physician_id)
VALUES (-1, 'admin', '$2a$10$6YQPWTolIgnuswCBpbhFXufcB2oafBzlov5u/w77WJhy839GF/Thq', null);

-- pass: password
INSERT INTO public.account(account_id, account_username, account_password, account_physician_id)
VALUES (-2, 'doktor', '$2a$10$EWJEKZum49Eqows4KcRhJupFfTNJmNqdT5zCSequa46IsEEjevNTG', -1);

-- pass: password
INSERT INTO public.account(account_id, account_username, account_password, account_physician_id)
VALUES (-3, 'lekar', '$2a$10$EWJEKZum49Eqows4KcRhJupFfTNJmNqdT5zCSequa46IsEEjevNTG', -2);

-- pass: password
INSERT INTO public.account(account_id, account_username, account_password, account_physician_id)
VALUES (-4, 'mag', '$2a$10$EWJEKZum49Eqows4KcRhJupFfTNJmNqdT5zCSequa46IsEEjevNTG', -3);


-- ACCOUNT_AUTHORITIES
INSERT INTO public.account_authorities(account_id, authority_id)
VALUES (-1, -1);

INSERT INTO public.account_authorities(account_id, authority_id)
VALUES (-2, -2);


-- INGREDIENTS
INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-1, 'Ing A');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-2, 'Ing B');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-3, 'Ing C');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-4, 'Ing D');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-5, 'Ing E');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-6, 'Ing F');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-7, 'Ing G');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-8, 'Ing H');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-9, 'Ing I');

INSERT INTO public.ingredient(ingredient_id, ingredient_name)
VALUES (-10, 'Ing J');


-- MEDICATION
INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-1, 'Antibiotic A,B,C', 'ANTIBIOTIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-2, 'Antibiotic A,B', 'ANTIBIOTIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-3, 'Antibiotic A', 'ANTIBIOTIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-4, 'Antibiotic', 'ANTIBIOTIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-5, 'Analgesic D,E,F', 'ANALGESIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-6, 'Analgesic D,E', 'ANALGESIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-7, 'Analgesic D', 'ANALGESIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-8, 'Analgesic', 'ANALGESIC');

INSERT INTO public.medication(medication_id, medication_name, medication_type)
VALUES (-9, 'Other', 'OTHER');


-- MEDICATION_INGREDIENTS

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-1, -1);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-1, -2);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-1, -3);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-2, -1);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-2, -2);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-3, -1);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-5, -4);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-5, -5);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-5, -6);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-6, -5);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-6, -6);

INSERT INTO public.medication_ingredient(medication_id, ingredient_id)
VALUES (-7, -6);


-- PATIENT_ALLERGIC_INGREDIENTS
INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-4, -1);

INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-5, -1);

INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-5, -2);

INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-6, -1);

INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-6, -2);

INSERT INTO public.patient_allergic_ingredients(person_id, ingredient_id)
VALUES (-6, -3);


-- SYMPTOMS
INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-1, 'runny nose');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-2, 'sore throat');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-3, 'headache');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-4, 'sneezing');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-5, 'coughing');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-6, 'trembling');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-7, 'body temperature');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-8, 'ache that spreads to ears');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-9, 'appetite loss');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-10, 'tiredness');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-11, 'yellow nasal phlegm');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-12, 'swollen eyes');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-13, 'high blood pressure');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-14, 'often urination');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-15, 'weight loss');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-16, 'nausea and vomiting');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-17, 'nocturia');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-18, 'legs and joints swelling');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-19, 'choking');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-20, 'chest pain');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-21, 'surgery recovery');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-22, 'diarrhea');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-23, 'had flu or fever in the last 60 days');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-24, 'has hypertension for more than 6 months');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-25, 'has diabetes');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-26, 'diagnosed with disease that has a raised body temperature as a symptom in the last 14 days ');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-27, 'diagnosed with disease that was medicated with antibiotics in the last 21 days ');

INSERT INTO public.symptom(symptom_id, symptom_name)
VALUES (-28, 'had high blood pressure in the last 6 months');


-- DISEASE
INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-1, 'FIRST', 'flu');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-2, 'FIRST', 'fever');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-3, 'FIRST', 'tonsil inflammation');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-4, 'FIRST', 'sinus infection');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-5, 'SECOND', 'hypertension');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-6, 'SECOND', 'diabetes');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-7, 'THIRD', 'chronic kidney disease');

INSERT INTO public.disease(disease_id, disease_group, disease_name)
VALUES (-8, 'THIRD', 'acute kidney injury');



-- DISEASE_GENERIC_SYMPTOMS
INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-1, -1);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-1, -2);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-1, -3);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-1, -4);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-1, -5);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -1);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -2);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -3);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -4);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -5);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -6);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-2, -7);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -2);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -3);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -6);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -7);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -8);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -9);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -10);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-3, -11);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -2);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -3);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -5);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -7);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -11);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-4, -12);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-5, -28);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-6, -10);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-6, -14);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-6, -15);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-6, -16);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-7, -10);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-7, -17);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-7, -18);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-7, -19);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-7, -20);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-8, -10);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-8, -18);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-8, -19);

INSERT INTO public.disease_generic_symptoms(disease_id, symptom_id)
VALUES (-8, -22);


-- DISEASE_SPECIFIC_SYMPTOMS
INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-4, -23);

INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-7, -24);

INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-7, -25);

INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-8, -21);

INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-8, -26);

INSERT INTO public.disease_specific_symptoms(disease_id, symptom_id)
VALUES (-8, -27);


-- DIAGNOSIS
INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-1, 40, '2018-01-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-2, 39, '2017-10-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-3, 38, '2017-07-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-4, 37.5, '2017-04-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-5, 37, '2017-01-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-6, 37, '2016-12-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-7, 40, '2018-01-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-8, 39, '2017-10-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-9, 38, '2017-07-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-10, 37.5, '2017-04-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-11, 37, '2017-01-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-12, 37, '2016-12-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-13, 37, '2018-05-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-14, 37, '2018-05-02');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-15, 37, '2018-05-03');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-16, 37, '2018-06-01');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-17, 37, '2018-06-02');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-18, 37, '2018-06-03');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-19, 37, '2018-06-03');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-20, 37, '2018-06-04');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-21, 37, '2018-06-05');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-22, 37, '2018-06-06');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-23, 37, '2018-06-07');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-24, 37, '2018-06-08');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-25, 37, '2018-06-09');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-26, 37, '2018-06-10');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-27, 37, '2018-06-11');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-28, 37, '2018-06-12');

INSERT INTO public.diagnosis(diagnosis_id, diagnosis_body_temperature, diagnosis_date)
VALUES (-29, 37, '2018-06-13');


-- DIAGNOSIS_PATIENT
INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -1);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -2);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -3);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -4);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -5);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-8, -6);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -7);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -8);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -9);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -10);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -11);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-9, -12);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -13);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -14);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -15);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -16);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -17);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-10, -18);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -19);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -20);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -21);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -22);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -23);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -24);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -25);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -26);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -27);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -28);

INSERT INTO public.diagnosis_patient(person_id, diagnosis_id)
VALUES (-11, -29);



-- DIAGNOSIS_PHYSICIAN
INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -1);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -2);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -3);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -4);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -5);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -6);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -7);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -8);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -9);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -10);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -11);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -12);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -13);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -14);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -15);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -16);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -17);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -18);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -19);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -20);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -21);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -22);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -23);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -24);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -25);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-3, -26);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-1, -27);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -28);

INSERT INTO public.diagnosis_physician(person_id, diagnosis_id)
VALUES (-2, -29);


-- DIAGNOSIS_DISEASES
INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-1, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-1, -5);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-2, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-2, -6);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-3, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-4, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-5, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-6, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-7, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-7, -5);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-8, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-9, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-10, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-11, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-12, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-13, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-14, -2);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-15, -3);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-16, -4);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-17, -5);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-18, -6);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-19, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-20, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-21, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-22, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-23, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-24, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-25, -1);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-26, -2);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-27, -2);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-28, -2);

INSERT INTO public.diagnosis_diseases(diagnosis_id, disease_id)
VALUES (-29, -2);


-- DIAGNOSIS_SYMPTOMS
INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-1, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-2, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-3, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-4, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-5, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-6, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-7, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-8, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-9, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-10, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-11, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-12, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-13, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-14, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-15, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-16, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-17, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-18, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-19, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-20, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-21, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-22, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-23, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-24, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-25, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-26, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-27, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-28, -1);

INSERT INTO public.diagnosis_symptoms(diagnosis_id, symptom_id)
VALUES (-29, -1);


-- DIAGNOSIS_MEDICATION
INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-1, -5);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-2, -6);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-3, -7);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-4, -1);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-5, -2);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-6, -5);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-7, -6);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-8, -7);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-9, -1);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-10, -2);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-11, -2);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-12, -2);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-13, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-14, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-15, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-16, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-17, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-18, -8);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-19, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-20, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-21, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-22, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-23, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-24, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-25, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-26, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-27, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-28, -4);

INSERT INTO public.diagnosis_medications(diagnosis_id, medication_id)
VALUES (-29, -4);