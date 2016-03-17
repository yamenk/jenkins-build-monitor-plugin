package net.serenitybdd.screenplay.jenkins.tasks.recipes;

import net.serenitybdd.screenplay.jenkins.tasks.CreateAFreestyleProject;
import net.serenitybdd.screenplay.jenkins.tasks.ScheduleABuild;
import net.serenitybdd.screenplay.jenkins.tasks.configuration.build_steps.ExecuteAShellScript;
import net.serenitybdd.screenplay.jenkins.tasks.configuration.build_steps.ShellScript;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class CreateAProjectAndScheduleABuildThatFails implements Task {

    @Step("{0} creates the '#projectName' project and schedules a build that fails")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                CreateAFreestyleProject.called(projectName).andConfigureItTo(
                        ExecuteAShellScript.that(ShellScript.Finishes_with_Error)
                ),
                ScheduleABuild.of(projectName)
        );
    }

    public CreateAProjectAndScheduleABuildThatFails(String projectName) {
        this.projectName = projectName;
    }

    private final String projectName;
}