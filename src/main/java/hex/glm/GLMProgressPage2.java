package hex.glm;


import water.*;
import water.api.Progress2;
import water.api.Request;

public class GLMProgressPage2 extends Progress2 {
  /** Return {@link Response} for finished job. */
  @Override
  protected Response jobDone(final Job job, final Key dst) {
    return GLMModelView.redirect(this, job.dest());
  }
  public static Response redirect(Request req, Key jobkey, Key dest) {
    return Response.redirect(req, "/2/GLMProgressPage2", JOB_KEY, jobkey, DEST_KEY, dest );
  }
  @Override public boolean toHTML( StringBuilder sb ) {
    Job jjob = Job.findJob(job_key);
    Value v = DKV.get(jjob.dest());
    if(v != null){
      GLMModel m = v.get();
      new GLMModelView(m).toHTML(sb);
    } else
      sb.append("<b>No model yet.</b>");
    return true;
  }
}
