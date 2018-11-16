package com.ekeitho.gitbasic.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ekeitho.gitbasic.databinding.DetailFragmentBinding

class DetailFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val db = DetailFragmentBinding.inflate(inflater, container, false)
        val args = DetailFragmentArgs.fromBundle(arguments)
        db.repo = args.repoName
        return db.root
    }

}