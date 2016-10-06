package uk.ivanc.archimvvm.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import braque.RESTShow;
import rx.Subscription;
import uk.ivanc.archimvvm.R;
import uk.ivanc.archimvvm.model.favoritething.braqued.IdGet;

/**
 * ViewModel for the InspectActivity
 */
public abstract class InspectThingViewModel<T extends RESTShow, F extends IdGet> implements ViewModel {

    private static final String TAG = "InspectThingViewModel";

    private Context context;
    private Subscription subscription;

    public InspectThingViewModel(Context context, F comingFrom) {
        this.context = context;
        // Trigger loading the rest of the user data as soon as the view model is created.
        // It's odd having to trigger this from here. Cases where accessing to the data model
        // needs to happen because of a change in the Activity/Fragment lifecycle
        // (i.e. an activity created) don't work very well with this MVVM pattern.
        // It also makes this class more difficult to test. Hopefully a better solution will be found
        loadFullThing(comingFrom);
    }

    @Override
    public void destroy() {
        this.context = null;
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(view);
    }

    abstract protected void loadFullThing(F thing);
}
