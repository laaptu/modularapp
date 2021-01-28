package org.ahivs.sample.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import org.ahivs.base.di.view.ViewModelFragment
import org.ahivs.sample.R
import javax.inject.Inject

//TODO remove all of this once the actual implementation is done
//this is just for demonstration
class SampleFragment : ViewModelFragment<SampleViewModel>(R.layout.sample_frag) {
    override val viewModel: SampleViewModel by viewModels {
        viewModelFactory
    }
}

class SampleUseCase @Inject constructor()
class SampleViewModel @Inject constructor(val sampleUseCase: SampleUseCase) : ViewModel()