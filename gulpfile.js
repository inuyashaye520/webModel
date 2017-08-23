var gulp = require('gulp');
var pug = require('gulp-pug');
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var sass = require('gulp-sass');
var uglify = require('gulp-uglify');
var merge = require('merge2');
var version = require('gulp-version-number');

var assets = 'src/main/assets';
var pugDest = 'target/classes/templates';
var assetsDest = 'target/classes/static/assets';

gulp.task('pug', function () {
    var pugs = gulp.src(assets + '/pug/**/*.pug')
        .pipe(pug({}))
        .pipe(version({replaces:[/#{VERSION}#/g]}))
        .pipe(rename({extname: '.ftl'}))
        .pipe(gulp.dest(pugDest));

    var tmpls = gulp.src([assets + '/module/**/*.pug'])
        .pipe(pug({}))
        .pipe(version({replaces:[/#{VERSION}#/g]}))
        .pipe(rename({extname: '.ftl'}))
        .pipe(gulp.dest(pugDest + '/tmpl'));

    return merge(pugs, tmpls);
});

gulp.task('css', function () {
    var admin = gulp.src(assets + '/scss/admin/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(assetsDest + '/css/admin'))
        .pipe(concat('admin.css'))
        .pipe(gulp.dest(assetsDest + '/css/admin'));

    var wx = gulp.src(assets + '/scss/wx/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(assetsDest + '/css/wx'))
        .pipe(concat('wx.css'))
        .pipe(gulp.dest(assetsDest + '/css/wx'));

    return merge(admin, wx);
});

gulp.task('admin.js', function () {
    var dest = assetsDest + '/admin/js';

    return gulp.src(['src/main/assets/module/admin/**/*.module.js',
        'src/main/assets/module/admin/**/*.js',
        'src/main/assets/js/admin.js'])
        .pipe(concat('admin.js'))
        .pipe(gulp.dest(dest))
        .pipe(rename('admin.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest(dest));
});

gulp.task('wx.js', function () {
    var dest = assetsDest + '/wx/js';

    return gulp.src(['src/main/assets/module/wx/**/*.module.js',
        'src/main/assets/module/wx/**/*.js',
        'src/main/assets/js/wx.js'])
        .pipe(concat('wx.js'))
        .pipe(gulp.dest(dest))
        .pipe(rename('wx.min.js'))
        .pipe(uglify())
        .pipe(gulp.dest(dest));
});

gulp.task('copy', function () {
    var img = gulp.src(assets + '/img/**/*')
        .pipe(gulp.dest(assetsDest + '/img'));

    var js = gulp.src(assets + '/js/**/*')
        .pipe(gulp.dest(assetsDest + '/js'));

    var lib = gulp.src(assets + '/lib/**/*')
        .pipe(gulp.dest(assetsDest + '/lib'));

    return merge(img, js, lib);
});

gulp.task('default', ['pug', 'css', 'admin.js', 'wx.js', 'copy']);
