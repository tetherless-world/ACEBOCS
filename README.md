# Ontology hygiene tests (Github template)
This is a template Github repository that enables ontologies to be tested against a set of predefined validations.

## Applying the tests to your project
You can use the contents in the repository to enable tests of your ontologies as part of continuous integration pipeline. This repository can be used in new projects (new Github repos) as well as included in existing projects. The instructions for both cases follow.

### New project (new Github repo)
When creating your new Github repo that host your project, make sure to select this project `tetherless-world/ontology-hygiene-tests` under the `Repository template` section. There is no need to check the option `Include all branches`. See the image below.

### Existing project (existing Github repo)
TBD.

### Verify that you repository has the correct hygiene tests content
Check the root directory of your repository. You should have (alogside your own content) the following content:
```
| .circleci
| | config.yml
| .hygiene
| | tests
| | - (a list of .sparql files)
| | docker-compose.yml
| | run_hygiene.sh
| | set_env.sh
```

## Configuring

### Update references
Update the following variables in `.hygiene/set_env.sh` to suit your project.
- `ONTPUB_SUBDIR` -
  This should be a relative path to the directory where your ontology sources are stored.
  The example ontology is stored in the `ontology` directory so the example config reads: `ONTPUB_SUBDIR=ontology`.
- `ONTPUB_EXCLUDED` -
  If you want to exclude some ontologies from testing, set this to a substring that matches those ontologies.
  E.g. if you don't want to test ontologies in `ontology/exclude`,
  you can set `ONTPUB_EXCLUDED=/exclude`.

### Enable CircleCI in your repository
You need to enable CircleCI in your repository. If it's already enabled, you can skip this step.
1. Go to https://app.circleci.com/dashboard and log in with yout Github account
2. Select the appropriate organization where your Github repository is
3. In the Project list, click `Set Up Project` to start the cofiguration of CircleCI for your repository
4. In the next screen, click `Use Existing Config` as you will be using an already created `.circleci/config.yml` configuration

## Testing
TBD.
