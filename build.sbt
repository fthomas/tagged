import org.typelevel.Dependencies._

lazy val gh = GitHubSettings(
  org = "fthomas",
  proj = "tagged",
  publishOrg = "org.typelevel",
  license = apache)

val vAll = Versions(versions, libraries, scalacPlugins)

lazy val prj = mkPrjFactory(rootSettings)
lazy val module = mkModuleFactory(gh.proj,
  mkConfig(rootSettings, commonJvmSettings, commonJsSettings))

lazy val rootPrj = project
  .configure(mkRootConfig(rootSettings, rootJVM))
  .aggregate(rootJVM, rootJS)
  .dependsOn(rootJVM, rootJS)

lazy val rootJVM = project
  .configure(mkRootJvmConfig(gh.proj, rootSettings, commonJvmSettings))
  .aggregate(coreJVM)
  .dependsOn(coreJVM)

lazy val rootJS = project
  .configure(mkRootJsConfig(gh.proj, rootSettings, commonJsSettings))
  .aggregate(coreJS)
  .dependsOn(coreJS)

lazy val core    = prj(coreM)
lazy val coreJVM = coreM.jvm
lazy val coreJS  = coreM.js
lazy val coreM   = module("core", CrossType.Pure)
  .settings(addTestLibs(vAll, "scalacheck", "shapeless"): _*)

lazy val rootSettings = buildSettings ++ scoverageSettings ++ styleSettings

lazy val buildSettings = sharedBuildSettings(gh, vAll)

lazy val scoverageSettings = sharedScoverageSettings(60)

lazy val styleSettings =
  scalariformSettings ++
  Seq(
    sourceDirectories in (Compile, SbtScalariform.ScalariformKeys.format) :=
      (sourceDirectories in Compile).value,
    sourceDirectories in (Test, SbtScalariform.ScalariformKeys.format) :=
      (sourceDirectories in Test).value
  )

lazy val commonJvmSettings = Seq()
lazy val commonJsSettings = Seq()
