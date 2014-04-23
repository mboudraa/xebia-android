package fr.xebia.app;

import android.content.Context;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean(scope = EBean.Scope.Singleton)
public class JobManagerProvider {

    @RootContext
    Context mContext;

    public JobManager jobManager;

    @AfterInject
    void configureJobManager() {
        Configuration configuration = new Configuration.Builder(mContext)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        jobManager = new JobManager(mContext, configuration);
    }


}
