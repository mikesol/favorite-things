package uk.ivanc.archimvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.databinding.RepositoryActivityBinding;
import uk.ivanc.archimvvm.model.Repository;
import uk.ivanc.archimvvm.viewmodel.GithubViewModel;
import uk.ivanc.archimvvm.viewmodel.InspectThingViewModel;

public class InspectActivity extends AppCompatActivity {

    private static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

    private RepositoryActivityBinding binding;
    private GithubViewModel inspectThingViewModel;

    public static Intent newIntent(Context context, Repository repository) {
        Intent intent = new Intent(context, InspectActivity.class);
        intent.putExtra(EXTRA_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.repository_activity);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Repository repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);
        inspectThingViewModel = new GithubViewModel(this, repository);
        binding.setViewModel(inspectThingViewModel);

        //Currently there is no way of setting an activity title using data binding
        setTitle(repository.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inspectThingViewModel.destroy();
    }
}
