module.exports = function(config){
    config.set({

            basePath : '../src/main/webapp',

            files : [
                'bower_components/angular/angular.js',
                'bower_components/angular-route/angular-route.js',
                'bower_components/angular-resource/angular-resource.js',
                'bower_components/angular-animate/angular-animate.js',
                'bower_components/angular-mocks/angular-mocks.js',
                'bower_components/closure-libraray/closure/goog/base.js',
                'bower_components/closure-libraray/closure/goog/deps.js',
                'deps.js',
                {pattern: '!(bower_components)/**/*.js', watched: true, included: false, served: true},
                '../../../test/unit/**/*.js'
                                        ],
                           autoWatch : true,
                           frameworks: ['jasmine'],
                           browsers : ['Chrome'],
                           plugins : [
                               'karma-chrome-launcher',
                               'karma-firefox-launcher',
                               'karma-jasmine',
                               'karma-coverage'
                           ],
                           junitReporter : {
                               outputFile: 'test_out/unit.xml',
                               suite: 'unit'
                           },
                           preprocessors : { '!(bower_components)/**/*.js': 'coverage' },
                                                     reporters : ['progress','coverage'],
                                                     coverageReporter: {
                                                     type : 'html',
                                                     dir : '../../../coverage/'
                                                     }
    });
    };
