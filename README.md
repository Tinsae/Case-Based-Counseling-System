# Case-Based-Counseling-System


The system reasons by calculating the similarity of new cases to past successful cases. The cases were collected from various departments of ASTU. Knowledge about appropriate case attributes, weights and similarity functions was collected from domain experts, and documentary sources. The system is enhanced by incorporating a database to store facts about fields of study, careers and universities. The prototype system uses the k-nearest-neighbours algorithm to retrieve top k similar cases then vote to recommend a set of fields, and then the solution is adapted to the interest of the student.

The research experimented a better way of eliciting interests of students by using the popular six code scheme, as case features. Using
these features a case base was constructed. The case base contains the cases of selected students, It can be used to advise new students aspiring to join higher education institutions in Ethiopia. The prototype system basically consults new students by comparing their case with selected cases successful students. To this end, the research evaluated many alternatives and selected appropriate similarity methods. The concept or ontology based similarity measurement were used for assessing similarity between fields, subjects and skills of students.
In addition a database was used to store facts about fields, careers and universities intake capacity. The careers have a one, two or three letter dominant interest areas. Every major is related with set of careers. After a set of similar cases are retrieved for a given new student, the results were adapted to the new student’s interest codes by retrieving facts about interest areas of careers and their related majors.
## System Diagram

![system_flow](https://user-images.githubusercontent.com/8983398/57889014-af213400-7801-11e9-8c8f-a80737c9f5d8.jpg)

## Screenshots
![querydialog](https://user-images.githubusercontent.com/8983398/57888654-beec4880-7800-11e9-9c7f-b0197d8d2d0d.jpg)
![similaritydialog](https://user-images.githubusercontent.com/8983398/57888656-beec4880-7800-11e9-9ba3-9245b3c00aa1.jpg)
![result](https://user-images.githubusercontent.com/8983398/57888655-beec4880-7800-11e9-8340-79650dbab246.jpg)
![solution](https://user-images.githubusercontent.com/8983398/57888657-beec4880-7800-11e9-9a2a-1b1dcef6159d.jpg)
![adapted_solution](https://user-images.githubusercontent.com/8983398/57888653-beec4880-7800-11e9-9035-b60c2e77ffc1.jpg)
