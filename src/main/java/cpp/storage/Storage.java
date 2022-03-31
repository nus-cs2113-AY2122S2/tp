package cpp.storage;

import cpp.model.ProjectList;
import cpp.model.project.Project;
import cpp.model.project.Todo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    /**
     * save the data based on certain style.
     *
     */
    public static void save(ProjectList projectList) {
        try {
            saveData(projectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read data from hard disk.
     * @throws IOException if the file/ file path does not exist.
     */
    public static ProjectList read() {
        ProjectList projectList = new ProjectList();
        try {
            projectList = readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    private static ProjectList readData() throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader("./src/data/projectList.txt"));
        } catch (IOException e) {
            createFile("./src/data");
        }
        BufferedReader in = new BufferedReader(new FileReader("./src/data/projectList.txt"));
        String projectLine;
        ProjectList projectList = new ProjectList();;
        int indexProject = 1;
        while ((projectLine = in.readLine()) != null) {
            String[] details = projectLine.split("`");
            int indexTodo = 1;
            assert (details.length == 4) : "Unable to load data! Data is incomplete!";

            String title = details[0];
            String todos = details[1];
            String deadline = details[2];
            final String gitHubLink = details[3];

            //add project to list
            projectList.addProject(title);

            String[] todoInfo = todos.split(",");
            //add todo to project
            for (int i = 0; i < (todoInfo.length) / 3; i++) {
                String todoDescrip = todoInfo[3 * i];
                String todoStatus = todoInfo[3 * i + 1];
                String todoDeadline = todoInfo[3 * i + 2];
                projectList.addTodoToProject(indexProject, todoDescrip);
                if (!todoDeadline.equalsIgnoreCase("No deadline specified")) {
                    projectList.addTodoDeadline(indexProject, i + 1, todoDeadline);
                }
                //mark todo as done
                if (todoStatus.equalsIgnoreCase("true")) { //this todo is marked as done
                    projectList.markTodoAsDone(indexProject, indexTodo);
                }
                indexTodo++;
            }
            //add deadline to project if deadline is specified
            if (!deadline.equalsIgnoreCase("No deadline specified")) {
                projectList.addProjectDeadline(title, deadline);
            }

            projectList.addGithubLink(title, gitHubLink);

            indexProject++;
        }
        in.close();
        return projectList;
    }

    private static void createFile(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            Files.createFile(Paths.get("./src/data/projectList.txt"));
            File file = new File("./src/data/projectList.txt");
            file.setWritable(true, false);
            file.setReadable(true, false);
            file.setExecutable(true, false);
        } catch (IOException e) {
            System.out.println("File could not be created.");
            System.exit(1);
        }
    }

    /**
     * storage format: title;description,status....description, status;deadline.
     *  @throws IOException if the file/ file path does not exist.
     */
    private static void saveData(ProjectList projectList) throws IOException {
        FileWriter writer = new FileWriter("./src/data/projectList.txt");
        int total = projectList.getProjectNo();
        for (int count = 0; count < total; count++) {
            Project project = projectList.getProject(count);
            String todoInfo = getTodoInfo(project.getTodos());
            String projectInfo = project.getTitle() + "`" + todoInfo + "`" + project.getDeadline()
                    + "`" + project.getGitHubLink();

            writer.write(projectInfo + System.lineSeparator());
        }
        writer.close();
    }


    /**
     * Get todo info based on certain format.
     * @param todos arraylist of todo type
     * @return string format: description, status, description, status, ...., description, status
     */
    private static String getTodoInfo(ArrayList<Todo> todos) {
        String todoInfo = "";
        if (todos.size() == 0) {
            return todoInfo;
        }
        for (Todo todo : todos) {
            String description = todo.getDescription();
            String status = todo.getDone();
            String deadline = todo.getDeadline();
            todoInfo += "," + description + "," + status + "," + deadline;
        }
        // remove the first comma
        todoInfo = todoInfo.substring(1);
        return todoInfo;
    }
}
