# Sample-Payment-Maps
A small repo that includes Google maps implementation &amp; [Razor Pay](https://razorpay.com/) to collect payment

### Demo
![Demo GIF](http://i.giphy.com/wGvQ83SI9wrPG.gif)

### Payment Screenshots

| Payment Selector | Pay with InstaMojo | Pay with RazorPay |
|-----------|----------|----------|
| <img src="https://github.com/jaydeep17/Sample-Payment-Maps/raw/master/screenshots/payment-selection.png" width="300"> | <img src="https://github.com/jaydeep17/Sample-Payment-Maps/raw/master/screenshots/instamojo.png" width="300"> | <img src="https://github.com/jaydeep17/Sample-Payment-Maps/raw/master/screenshots/razorpay.png" width="300"> |


## About the code
- Model View Presenter (MVP) Architecture 
- Android [Databinding](https://developer.android.com/topic/libraries/data-binding/index.html) for XML views
- Uses [Dagger2](http://google.github.io/dagger/) for Dependency Injection
- [RetroLambda](https://github.com/evant/gradle-retrolambda) for Java 8 style lambdas
- Uses [Android Support Annotations](http://tools.android.com/tech-docs/support-annotations) for better variable description

## How to Run it?
The app requires a Razor Pay public key to compile.

Once you have it, create a file `app/gradle.properties`, (there's a sample file given in the repo `app/gradle.properties.example`) and paste the key in there.

Your file should look something like
```
str.razorpay_public_key=rzp_test_*******
```

Then go ahead & run the project.
