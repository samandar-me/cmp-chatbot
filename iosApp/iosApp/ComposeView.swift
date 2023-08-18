//
//  ComposeView.swift
//  iosApp
//
//  Created by Samandar on 18/08/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct ComposeView : UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
        }
    func makeUIViewController(context: Context) -> some UIViewController {
            MainViewControllerKt.MainViewController()
    }
}
